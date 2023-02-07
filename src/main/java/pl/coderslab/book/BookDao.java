package pl.coderslab.book;

import org.springframework.stereotype.Repository;
import pl.coderslab.author.Author;
import pl.coderslab.publisher.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void delete(Book book) {
        Book persistedBook = entityManager.contains(book) ? book : entityManager.merge(book);
        entityManager.remove(persistedBook);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b join fetch b.publisher", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllByRating(int rating) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.rating = :rating", Book.class);
        query.setParameter("rating", rating);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllBooksThatHaveAnyPublisher() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.publisher is not null", Book.class);
        return query.getResultList();
    }

    public List<Book> findAllBooksForGivenPublisher(Publisher publisher) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.publisher = :publisher", Book.class);
        query.setParameter("publisher", publisher);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllBooksForGivenAuthor(Author author) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b join fetch b.authors " +
                "where :author member of b.authors", Book.class);
        query.setParameter("author", author);
        List<Book> books = query.getResultList();
        return books;
    }
}
