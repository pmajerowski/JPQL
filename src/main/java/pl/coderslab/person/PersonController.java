package pl.coderslab.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }


    @GetMapping("/person/add")
//    @ResponseBody
    public String addPersonForm(Model model) {
        Person predefinedPerson = new Person();
        predefinedPerson.setLogin("login");
        model.addAttribute("person", predefinedPerson);
        return "/person/add";
    }

    @PostMapping("/person/add")
    @ResponseBody
    public String addPerson(Person person, Model model) {
        personDao.savePerson(person);
        return person.toString();
    }


    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable long id) {
        Person person = personDao.findById(id);
//        PersonDetails personDetails = personDetailsDao.findById(person.getDetails().getId());
//        personDetailsDao.delete(personDetails);
        personDao.delete(person);
        return "deleted";
    }

    @RequestMapping("/person/update/{id}/{login}")
    @ResponseBody
    public String updatePerson(@PathVariable long id, @PathVariable String login) {
        Person person = personDao.findById(id);
        person.setLogin(login);
        personDao.update(person);
        return person.toString();
    }


}
