package ly.kaizen.lad.repository;

import ly.kaizen.lad.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    List<PhoneNumber> findByOwnerTypeAndOwnerId(String ownerType, Long ownerId);
}
