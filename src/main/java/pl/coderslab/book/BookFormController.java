package pl.coderslab.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;
import pl.coderslab.publisher.Publisher;
import pl.coderslab.publisher.PublisherDao;

import java.util.List;

@Controller
public class BookFormController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookFormController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/book/new")
    public String newBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "/book/new";
    }

    @PostMapping("/book/save")
    public String saveBook(Book book) {
        bookDao.saveBook(book);
        return "redirect:/book/list";
    }

    @GetMapping("/book/list")
    public String listBooks(Model model) {
        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);
        return "/book/list";
    }

    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable long id, Model model) {
        Book bookToEdit = bookDao.findById(id);
        model.addAttribute("bookToEdit", bookToEdit);
        return "/book/edit";
    }

    @PostMapping("/book/edit/save/{id}")
    public String editBook(Book book, @PathVariable long id) {
        book.setId(id);
        bookDao.update(book);
        return "redirect:/book/list";
//        return book.toString() + " , id= " + id;
    }


    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        return publisherDao.findAll();
    }

    @GetMapping("/delete/confirm/{id}")
    public String deleteConfirm(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "/book/deleteConfirm";
    }

    @ModelAttribute("authors")
    public List<Author> authors() {
        return authorDao.findAll();
    }

}