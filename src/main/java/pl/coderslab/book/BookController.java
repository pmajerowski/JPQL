package pl.coderslab.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;
import pl.coderslab.publisher.Publisher;
import pl.coderslab.publisher.PublisherDao;

import java.util.List;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private  final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
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
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
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
}
