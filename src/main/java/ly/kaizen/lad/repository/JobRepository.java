package ly.kaizen.lad.repository;

import ly.kaizen.lad.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByQueueAndReservedAtIsNullOrderByAvailableAtAsc(String queue);
}
