package ly.kaizen.lad.service;

import ly.kaizen.lad.model.PhoneNumber;

import java.util.Optional;
import java.util.List;

public interface PhoneNumberService {

    PhoneNumber createPhoneNumber(PhoneNumber phoneNumber);

    Optional<PhoneNumber> getPhoneNumberById(Long id);

    List<PhoneNumber> getPhoneNumbersByOwner(String ownerType, Long ownerId);

    List<PhoneNumber> getAllPhoneNumbers();

    PhoneNumber updatePhoneNumber(Long id, PhoneNumber phoneNumber);

    void deletePhoneNumber(Long id);
}
