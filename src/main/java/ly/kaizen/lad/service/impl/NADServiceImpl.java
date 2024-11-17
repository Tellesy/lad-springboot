package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.dto.*;
import ly.kaizen.lad.service.NADService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class NADServiceImpl implements NADService {

    private final WebClient webClient;

    @Value("${nad.base-url}")
    private String nadBaseUrl;

    public NADServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(nadBaseUrl).build();
    }

    @Override
    public NADResponseDTO enrollIndividual(EnrollIndividualRequestDTO enrollRequest) {
        return sendPostRequest("/api/v1/individuals/enroll", enrollRequest);
    }

    @Override
    public NADResponseDTO updateIndividual(UpdateIndividualRequestDTO updateRequest) {
        return sendPostRequest("/api/v1/individuals/update", updateRequest);
    }

    @Override
    public NADResponseDTO lookupIndividual(String schema, String id) {
        String endpoint = String.format("/api/v1/individuals/lookup?schema=%s&id=%s", schema, id);
        return sendGetRequest(endpoint);
    }

    @Override
    public NADResponseDTO enrollMerchant(EnrollMerchantRequestDTO enrollRequest) {
        return sendPostRequest("/api/v1/merchants/enroll", enrollRequest);
    }

    @Override
    public NADResponseDTO updateMerchant(UpdateMerchantRequestDTO updateRequest) {
        return sendPostRequest("/api/v1/merchants/update", updateRequest);
    }

    @Override
    public NADResponseDTO lookupMerchant(String schema, String id) {
        String endpoint = String.format("/api/v1/merchants/lookup?schema=%s&id=%s", schema, id);
        return sendGetRequest(endpoint);
    }

    private NADResponseDTO sendPostRequest(String endpoint, Object requestBody) {
        try {
            return webClient.post()
                    .uri(endpoint)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(NADResponseDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            return new NADResponseDTO("error", ex.getResponseBodyAsString(), null);
        }
    }

    private NADResponseDTO sendGetRequest(String endpoint) {
        try {
            return webClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .bodyToMono(NADResponseDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            return new NADResponseDTO("error", ex.getResponseBodyAsString(), null);
        }
    }
}
