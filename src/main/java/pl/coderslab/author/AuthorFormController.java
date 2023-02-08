package pl.coderslab.author;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.book.Book;
import pl.coderslab.book.BookDao;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorFormController {
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public AuthorFormController(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/author/new")
    public String newAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "/author/new";
    }

    @PostMapping("/author/save")
    public String saveAuthor(@Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/author/new";
        }
        authorDao.saveAuthor(author);
        return "redirect:/author/list";
    }

    @GetMapping("/author/list")
    public String listAuthors(Model model) {
        List<Author> authors = authorDao.findAll();
        model.addAttribute("authors", authors);
        return "/author/list";
    }

    @GetMapping("/author/edit/{id}")
    public String editAuthor(@PathVariable long id, Model model) {
        Author authorToEdit = authorDao.findById(id);
        model.addAttribute("authorToEdit", authorToEdit);
        return "/author/edit";
    }

    @PostMapping("/author/edit/save/{id}")
    public String editAuthor(Author author, @PathVariable long id) {
        author.setId(id);
        authorDao.update(author);
        return "redirect:/author/list";
//        return book.toString() + " , id= " + id;
    }


    @ModelAttribute("books")
    public List<Book> books() {
        return bookDao.findAll();
    }

    @GetMapping("/author/confirm/{id}")
    public String deleteConfirm(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "/author/deleteConfirm";
    }

//    @ModelAttribute("authors")
//    public List<Author> authors() {
//        return authorDao.findAll();
//    }


}
