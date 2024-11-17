package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Merchant;
import ly.kaizen.lad.repository.MerchantRepository;
import ly.kaizen.lad.service.MerchantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public Merchant createMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Optional<Merchant> getMerchantById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Optional<Merchant> getMerchantByNid(String nid) {
        return merchantRepository.findAll()
                .stream()
                .filter(merchant -> merchant.getNid().equals(nid))
                .findFirst();
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant updateMerchant(Long id, Merchant merchant) {
        return merchantRepository.findById(id)
                .map(existingMerchant -> {
                    existingMerchant.setName(merchant.getName());
                    existingMerchant.setTradeLicenseNumber(merchant.getTradeLicenseNumber());
                    existingMerchant.setAddress(merchant.getAddress());
                    existingMerchant.setEncryptedPin(merchant.getEncryptedPin());
                    return merchantRepository.save(existingMerchant);
                })
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found with id: " + id));
    }

    @Override
    public void deleteMerchant(Long id) {
        if (merchantRepository.existsById(id)) {
            merchantRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Merchant not found with id: " + id);
        }
    }
}
