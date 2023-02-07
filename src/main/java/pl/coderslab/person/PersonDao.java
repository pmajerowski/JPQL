package pl.coderslab.person;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void savePerson(Person person) {
        entityManager.persist(person);
    }

    public Person findById(long id) {
        return entityManager.find(Person.class, id);
    }

    public void delete(Person person) {
        Person persistedPerson = entityManager.contains(person) ? person : entityManager.merge(person);
        entityManager.remove(persistedPerson);
    }

    public void update(Person person) {
        entityManager.merge(person);
    }
}
