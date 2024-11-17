package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.PhoneNumber;
import ly.kaizen.lad.repository.PhoneNumberRepository;
import ly.kaizen.lad.service.PhoneNumberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public PhoneNumber createPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    @Override
    public Optional<PhoneNumber> getPhoneNumberById(Long id) {
        return phoneNumberRepository.findById(id);
    }

    @Override
    public List<PhoneNumber> getPhoneNumbersByOwner(String ownerType, Long ownerId) {
        return phoneNumberRepository.findByOwnerTypeAndOwnerId(ownerType, ownerId);
    }

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    @Override
    public PhoneNumber updatePhoneNumber(Long id, PhoneNumber phoneNumber) {
        return phoneNumberRepository.findById(id)
                .map(existingPhoneNumber -> {
                    existingPhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());
                    existingPhoneNumber.setOwnerType(phoneNumber.getOwnerType());
                    existingPhoneNumber.setOwnerId(phoneNumber.getOwnerId());
                    return phoneNumberRepository.save(existingPhoneNumber);
                })
                .orElseThrow(() -> new IllegalArgumentException("PhoneNumber not found with id: " + id));
    }

    @Override
    public void deletePhoneNumber(Long id) {
        if (phoneNumberRepository.existsById(id)) {
            phoneNumberRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("PhoneNumber not found with id: " + id);
        }
    }
}
