package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Alias;

import java.util.Optional;
import java.util.List;

public interface AliasService {

    Alias createAlias(Alias alias);

    Optional<Alias> getAliasById(Long id);

    Optional<Alias> getAliasByValue(String alias);

    List<Alias> getAllAliases();

    List<Alias> getAliasesByAccountId(Long accountId);

    Alias updateAlias(Long id, Alias alias);

    void deleteAlias(Long id);
}
