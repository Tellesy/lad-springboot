package ly.kaizen.lad.dto;

import lombok.Data;

@Data
public class UpdateIndividualRequestDTO {
    private String schema;
    private String id; // ID or alias to identify the individual
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nid; // National ID
    private String phoneNumber;
    private String passportNumber;
    private String iban;
    // Add other fields as required by the NAD API
}
