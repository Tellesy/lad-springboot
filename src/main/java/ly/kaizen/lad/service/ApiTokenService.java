package ly.kaizen.lad.service;

import ly.kaizen.lad.model.ApiToken;

import java.util.Optional;
import java.util.List;

public interface ApiTokenService {

    ApiToken createApiToken(ApiToken apiToken);

    Optional<ApiToken> getApiTokenById(Long id);

    Optional<ApiToken> getApiTokenByToken(String token);

    List<ApiToken> getAllApiTokens();

    ApiToken updateApiToken(Long id, ApiToken apiToken);

    void deleteApiToken(Long id);
}