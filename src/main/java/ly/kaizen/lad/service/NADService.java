package ly.kaizen.lad.service;

import ly.kaizen.lad.dto.*;

public interface NADService {

    NADResponseDTO enrollIndividual(EnrollIndividualRequestDTO enrollRequest);

    NADResponseDTO updateIndividual(UpdateIndividualRequestDTO updateRequest);

    NADResponseDTO lookupIndividual(String schema, String id);

    NADResponseDTO enrollMerchant(EnrollMerchantRequestDTO enrollRequest);

    NADResponseDTO updateMerchant(UpdateMerchantRequestDTO updateRequest);

    NADResponseDTO lookupMerchant(String schema, String id);
}
