package pl.coderslab.author;

import org.springframework.stereotype.Repository;
import pl.coderslab.publisher.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void delete(Author author) {
        Author persistedAuthor = entityManager.contains(author) ? author : entityManager.merge(author);
        entityManager.remove(persistedAuthor);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }

    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        List<Author> authors = query.getResultList();
        return authors;
    }
}
