package pl.coderslab.author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.book.Book;
import pl.coderslab.book.BookController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class AuthorController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final Validator validator;
    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao, Validator validator) {
        this.authorDao = authorDao;
        this.validator = validator;
    }

    @GetMapping("/author/add")
    @ResponseBody
    public String addAuthor() {
        Author author = new Author();
        author.setFirstName("Ernest");
        author.setLastName("Hemingway");
        authorDao.saveAuthor(author);
        return "Created author id: " + author.getId();
    }

    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @RequestMapping("/author/delete/{id}")
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "redirect:/author/list";
    }

    @RequestMapping("/author/update/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName) {
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return author.toString();
    }

    @GetMapping("/author/validate")
//    @ResponseBody
    public String validate(Model model) {
        Author author = new Author();
        author.setLastName("AB");
        author.setPesel("5656tt");
        author.setEmail("erererer.pl");
        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        if (!violations.isEmpty()) {
            model.addAttribute("violations", violations);
            for (ConstraintViolation<Author> constraintViolation : violations) {
                logger.info(constraintViolation.getPropertyPath() + " "
                        + constraintViolation.getMessage()); }
        } else {
            model.addAttribute("violations", violations);
        }
        return "author/validate";
    }
}
