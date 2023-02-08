package pl.coderslab.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.author.Author;
import pl.coderslab.category.Category;
import pl.coderslab.publisher.Publisher;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByRating(int rating);

    List<Book> findByTitle(String title);

    List<Book> findByCategory(Category category);

    List<Book> findByCategoryId(long categoryId);

    List<Book> findByAuthors(Author author);

    List<Book> findByPublisher(Publisher publisher);

    Optional<Book> findFirstByCategoryOrderByTitle(Category category);

}