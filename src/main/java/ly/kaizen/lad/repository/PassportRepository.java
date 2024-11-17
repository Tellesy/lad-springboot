package ly.kaizen.lad.repository;

import ly.kaizen.lad.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
    Optional<Passport> findByPassportNumber(String passportNumber);
}
