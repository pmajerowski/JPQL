package pl.coderslab.person;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {
    private final PersonDao personDao;
    private  final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @GetMapping("/person/add")
    @ResponseBody
    public String addPerson() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setCity("Warsaw");
        personDetails.setFirstName("Maria");
        personDetails.setLastName("Rokita");
        personDetails.setStreet("Brzozowa");
        personDetails.setStreetNumber(44);
        personDetailsDao.savePersonDetails(personDetails);

        Person person = new Person();
        person.setDetails(personDetails);

        person.setEmail("test@wp.pl");
        person.setLogin("rybak101");
        person.setPassword("pawiany_wchodza_na_sciany087");
        personDao.savePerson(person);
        return "Created person id: " + person.getId() + "\n" + person.toString();
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
