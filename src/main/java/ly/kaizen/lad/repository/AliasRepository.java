package ly.kaizen.lad.repository;

import ly.kaizen.lad.model.Alias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AliasRepository extends JpaRepository<Alias, Long> {
    Optional<Alias> findByAlias(String alias);
}
