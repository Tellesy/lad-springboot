package ly.kaizen.lad.dto;

import lombok.Data;

@Data
public class EnrollIndividualRequestDTO {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nid; // National ID
    private String birthDate;
    private String phoneNumber;
    private String passportNumber;
    private String iban;
    private String institutionCode;
    // Add other fields as required by the NAD API
}
