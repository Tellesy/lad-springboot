package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.ApiToken;
import ly.kaizen.lad.repository.ApiTokenRepository;
import ly.kaizen.lad.service.ApiTokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiTokenServiceImpl implements ApiTokenService {

    private final ApiTokenRepository apiTokenRepository;

    public ApiTokenServiceImpl(ApiTokenRepository apiTokenRepository) {
        this.apiTokenRepository = apiTokenRepository;
    }

    @Override
    public ApiToken createApiToken(ApiToken apiToken) {
        return apiTokenRepository.save(apiToken);
    }

    @Override
    public Optional<ApiToken> getApiTokenById(Long id) {
        return apiTokenRepository.findById(id);
    }

    @Override
    public Optional<ApiToken> getApiTokenByToken(String token) {
        return apiTokenRepository.findByToken(token);
    }

    @Override
    public List<ApiToken> getAllApiTokens() {
        return apiTokenRepository.findAll();
    }

    @Override
    public ApiToken updateApiToken(Long id, ApiToken apiToken) {
        return apiTokenRepository.findById(id)
                .map(existingToken -> {
                    existingToken.setInstitutionName(apiToken.getInstitutionName());
                    existingToken.setPermissions(apiToken.getPermissions());
                    existingToken.setExpiresAt(apiToken.getExpiresAt());
                    return apiTokenRepository.save(existingToken);
                })
                .orElseThrow(() -> new IllegalArgumentException("ApiToken not found with id: " + id));
    }

    @Override
    public void deleteApiToken(Long id) {
        if (apiTokenRepository.existsById(id)) {
            apiTokenRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ApiToken not found with id: " + id);
        }
    }
}