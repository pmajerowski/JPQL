package pl.coderslab.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;
import pl.coderslab.category.CategoryRepository;
import pl.coderslab.publisher.Publisher;
import pl.coderslab.publisher.PublisherDao;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final Validator validator;
    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository, PublisherDao publisherDao, AuthorDao authorDao, Validator validator) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.validator = validator;
    }

    @GetMapping("/book/add")
    @ResponseBody
    public String addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Janusz Edition Ltd.");
        publisherDao.savePublisher(publisher);
        Author author1 = authorDao.findById(1);
        Author author2 = authorDao.findById(2);
        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setDescription("Definitely worth reading");
        book.setPublisher(publisher);
        book.getAuthors().add(author1);
        book.getAuthors().add(author2);
        bookRepository.save(book);
        return book.toString();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookRepository.getOne(id);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
        return "deleted";
    }

    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookRepository.getOne(id);
        book.setTitle(title);
        bookRepository.save(book);
        return book.toString();
    }

    @GetMapping("/book/all")
    @ResponseBody
    public String getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.toString();
    }

    @GetMapping("/book/rating/{rating}")
    @ResponseBody
    public String getAllBooksByRating(@PathVariable int rating) {
        List<Book> books = bookRepository.findByRating(rating);
        return books.toString();
    }

    @GetMapping("/book/title/{title}")
    @ResponseBody
    public String getAllBooksByTitle(@PathVariable String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.toString();
    }

    @GetMapping("/book/category/{categoryId}")
    @ResponseBody
    public String getAllBooksByCategoryId(@PathVariable long categoryId) {
        List<Book> books = bookRepository.findByCategoryId(categoryId);
        return books.toString();
    }

    @GetMapping("/book/category")
    @ResponseBody
    public String getAllBooksByCategory() {
//        Category category = categoryRepository.getOne(3L);
        List<Book> books = categoryRepository.findById(1L)
                .map(c -> bookRepository.findByCategory(c))
                .orElse(List.of());
        return books.toString();
    }

    @GetMapping("/book/validate")
    @ResponseBody
    public String validate() {
        Book book = new Book();
        book.setTitle("AB");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (!violations.isEmpty()) {
            logger.info("Book is invalid");
            for (ConstraintViolation<Book> constraintViolation : violations) {
                logger.info(constraintViolation.getPropertyPath() + " "
                        + constraintViolation.getMessage()); }
        } else {
            logger.info("Book is valid");
        }
        return "validated";
    }
}