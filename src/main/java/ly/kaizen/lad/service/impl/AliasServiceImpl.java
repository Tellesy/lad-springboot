package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Alias;
import ly.kaizen.lad.repository.AliasRepository;
import ly.kaizen.lad.service.AliasService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AliasServiceImpl implements AliasService {

    private final AliasRepository aliasRepository;

    public AliasServiceImpl(AliasRepository aliasRepository) {
        this.aliasRepository = aliasRepository;
    }

    @Override
    public Alias createAlias(Alias alias) {
        return aliasRepository.save(alias);
    }

    @Override
    public Optional<Alias> getAliasById(Long id) {
        return aliasRepository.findById(id);
    }

    @Override
    public Optional<Alias> getAliasByValue(String alias) {
        return aliasRepository.findByAlias(alias);
    }

    @Override
    public List<Alias> getAllAliases() {
        return aliasRepository.findAll();
    }

    @Override
    public List<Alias> getAliasesByAccountId(Long accountId) {
        return aliasRepository.findAll()
                .stream()
                .filter(a -> a.getAccount().getId().equals(accountId))
                .toList();
    }

    @Override
    public Alias updateAlias(Long id, Alias alias) {
        return aliasRepository.findById(id)
                .map(existingAlias -> {
                    existingAlias.setAlias(alias.getAlias());
                    existingAlias.setAccount(alias.getAccount());
                    return aliasRepository.save(existingAlias);
                })
                .orElseThrow(() -> new IllegalArgumentException("Alias not found with id: " + id));
    }

    @Override
    public void deleteAlias(Long id) {
        if (aliasRepository.existsById(id)) {
            aliasRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Alias not found with id: " + id);
        }
    }
}
