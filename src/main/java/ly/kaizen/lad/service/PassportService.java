package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Passport;

import java.util.Optional;
import java.util.List;

public interface PassportService {

    Passport createPassport(Passport passport);

    Optional<Passport> getPassportById(Long id);

    Optional<Passport> getPassportByNumber(String passportNumber);

    List<Passport> getPassportsByOwner(String ownerType, Long ownerId);

    List<Passport> getAllPassports();

    Passport updatePassport(Long id, Passport passport);

    void deletePassport(Long id);
}
