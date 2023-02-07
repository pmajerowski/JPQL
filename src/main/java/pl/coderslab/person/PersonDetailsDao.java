package pl.coderslab.person;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Repository
@Transactional
public class PersonDetailsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void savePersonDetails(PersonDetails person) {
        entityManager.persist(person);
    }

    public PersonDetails findById(long id) {
        return entityManager.find(PersonDetails.class, id);
    }

    public void delete(PersonDetails person) {
        PersonDetails persistedPerson = entityManager.contains(person) ? person : entityManager.merge(person);
        entityManager.remove(persistedPerson);
    }

    public void update(PersonDetails person) {
        entityManager.merge(person);
    }

}
