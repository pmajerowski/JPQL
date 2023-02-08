package pl.coderslab.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findFirstByEmail(String email);

    Optional<Author> findFirstByPesel(String pesel);

    List<Author> findAllByLastName(String lastName);

}