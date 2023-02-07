package pl.coderslab.publisher;

import org.springframework.stereotype.Repository;
import pl.coderslab.book.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void savePublisher(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void delete(Publisher publisher) {
        Publisher persistedPublisher = entityManager.contains(publisher) ? publisher : entityManager.merge(publisher);
        entityManager.remove(persistedPublisher);
    }

    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public List<Publisher> findAll() {
        TypedQuery<Publisher> query = entityManager.createQuery("select p from Publisher p", Publisher.class);
        List<Publisher> publishers = query.getResultList();
        return publishers;
    }
}