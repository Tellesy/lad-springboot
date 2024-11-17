package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Merchant;

import java.util.Optional;
import java.util.List;

public interface MerchantService {

    Merchant createMerchant(Merchant merchant);

    Optional<Merchant> getMerchantById(Long id);

    Optional<Merchant> getMerchantByNid(String nid);

    List<Merchant> getAllMerchants();

    Merchant updateMerchant(Long id, Merchant merchant);

    void deleteMerchant(Long id);
}
