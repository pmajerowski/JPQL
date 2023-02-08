package pl.coderslab.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.author.Author;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class PublisherController {
    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    private final Validator validator;
    private final PublisherDao publisherDao;

    public PublisherController(Validator validator, PublisherDao publisherDao) {
        this.validator = validator;
        this.publisherDao = publisherDao;
    }

    @GetMapping("/publisher/add")
    @ResponseBody
    public String addPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("The great publisher from America");
        publisherDao.savePublisher(publisher);
        return "Created publisher id: " + publisher.getId();
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/delete/{id}")
    public String deletePublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "redirect:/publisher/list";
    }

    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String updatePublisher(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        return publisher.toString();
    }

    @GetMapping("/publisher/validate")
//    @ResponseBody
    public String validate(Model model) {
        Publisher publisher = new Publisher();
        publisher.setName("AB");
        publisher.setNip("dgeg");
        publisher.setRegon("erererer.pl");
        Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);

        if (!violations.isEmpty()) {
            model.addAttribute("violations", violations);
            for (ConstraintViolation<Publisher> constraintViolation : violations) {
                logger.info(constraintViolation.getPropertyPath() + " "
                        + constraintViolation.getMessage()); }
        } else {
            model.addAttribute("violations", violations);
        }
        return "publisher/validate";
    }
}
