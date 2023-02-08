package pl.coderslab.publisher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.author.Author;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PublisherFormController {
    private final PublisherDao publisherDao;

    public PublisherFormController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping("/publisher/new")
    public String newPublisher(Model model) {
        Publisher publisher = new Publisher();
        model.addAttribute("publisher", publisher);
        return "/publisher/new";
    }

    @PostMapping("/publisher/save")
    public String savePublisher(@Valid Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/publisher/new";
        }
        publisherDao.savePublisher(publisher);
        return "redirect:/publisher/list";
    }

    @GetMapping("/publisher/list")
    public String listPublishers(Model model) {
        List<Publisher> publishers = publisherDao.findAll();
        model.addAttribute("publishers", publishers);
        return "/publisher/list";
    }

    @GetMapping("/publisher/edit/{id}")
    public String editPublisher(@PathVariable long id, Model model) {
        Publisher publisherToEdit = publisherDao.findById(id);
        model.addAttribute("publisherToEdit", publisherToEdit);
        return "/publisher/edit";
    }

    @PostMapping("/publisher/edit/save/{id}")
    public String editPublisher(Publisher publisher, @PathVariable long id) {
        publisher.setId(id);
        publisherDao.update(publisher);
        return "redirect:/publisher/list";
//        return book.toString() + " , id= " + id;
    }


    @GetMapping("/publisher/confirm/{id}")
    public String deleteConfirm(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "/publisher/deleteConfirm";
    }

//    @ModelAttribute("authors")
//    public List<Author> authors() {
//        return authorDao.findAll();
//    }


}
