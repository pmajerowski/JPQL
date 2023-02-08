package pl.coderslab.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findFirstByNip(String nip);

    Optional<Publisher> findFirstByRegon(String regon);
}
