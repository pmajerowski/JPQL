package pl.coderslab.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;
import pl.coderslab.publisher.Publisher;
import pl.coderslab.publisher.PublisherDao;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private  final AuthorDao authorDao;
    private final Validator validator;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, Validator validator) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;

        this.validator = validator;
    }

    @GetMapping("/book/add")
    @ResponseBody
    public String addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Some publisher");
        publisherDao.savePublisher(publisher);

        Author author1 = authorDao.findById(1);
        Author author2 = authorDao.findById(2);

        Book book = new Book();
        book.setPublisher(publisher);
        book.setTitle("Thinking in Java");
        book.setDescription("Definitely worth reading");
        book.getAuthors().add(author1);
        book.getAuthors().add(author2);

        bookDao.saveBook(book);
        return "Created book id: " + book.getId() + "\n" + book.toString();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
//    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "redirect:/book/list";
    }

    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @GetMapping("/book/all")
    @ResponseBody
    public String allBooks() {
        return bookDao.findAll().toString();
    }

    @GetMapping("/book/rating/{rating}")
    @ResponseBody
    public String findAllBooksByRating(@PathVariable int rating) {
        return bookDao.findAllByRating(rating).toString();
    }

    @GetMapping("/book/publisher")
    @ResponseBody
    public String findBooksThatHaveAnyPublisher() {
        return bookDao.findAllBooksThatHaveAnyPublisher().toString();
    }

    @GetMapping("/book/publisher/{id}")
    @ResponseBody
    public String findBooksByPublisherId(@PathVariable int id) {
        Publisher publisher = publisherDao.findById(id);
        return bookDao.findAllBooksForGivenPublisher(publisher).toString();
    }

    @GetMapping("/book/author/{id}")
    @ResponseBody
    public String findBooksByAuthorId(@PathVariable int id) {
        Author author = authorDao.findById(id);
        return bookDao.findAllBooksForGivenAuthor(author).toString();
    }

    @GetMapping("/book/validate")
//    @ResponseBody
    public String validate(Model model) {
        Book book = new Book();
        book.setTitle("AB");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
            model.addAttribute("violations", violations);
            for (ConstraintViolation<Book> constraintViolation : violations) {
                logger.info(constraintViolation.getPropertyPath() + " "
                        + constraintViolation.getMessage()); }
        } else {
            model.addAttribute("violations", violations);
        }
        return "book/validate";
    }
}
