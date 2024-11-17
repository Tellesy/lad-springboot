package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Passport;
import ly.kaizen.lad.repository.PassportRepository;
import ly.kaizen.lad.service.PassportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    public PassportServiceImpl(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public Passport createPassport(Passport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public Optional<Passport> getPassportById(Long id) {
        return passportRepository.findById(id);
    }

    @Override
    public Optional<Passport> getPassportByNumber(String passportNumber) {
        return passportRepository.findByPassportNumber(passportNumber);
    }

    @Override
    public List<Passport> getPassportsByOwner(String ownerType, Long ownerId) {
        return passportRepository.findAll()
                .stream()
                .filter(passport -> passport.getOwnerType().equals(ownerType) && passport.getOwnerId().equals(ownerId))
                .toList();
    }

    @Override
    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    @Override
    public Passport updatePassport(Long id, Passport passport) {
        return passportRepository.findById(id)
                .map(existingPassport -> {
                    existingPassport.setPassportNumber(passport.getPassportNumber());
                    existingPassport.setFullName(passport.getFullName());
                    existingPassport.setExpiryDate(passport.getExpiryDate());
                    existingPassport.setOwnerType(passport.getOwnerType());
                    existingPassport.setOwnerId(passport.getOwnerId());
                    return passportRepository.save(existingPassport);
                })
                .orElseThrow(() -> new IllegalArgumentException("Passport not found with id: " + id));
    }

    @Override
    public void deletePassport(Long id) {
        if (passportRepository.existsById(id)) {
            passportRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Passport not found with id: " + id);
        }
    }
}
