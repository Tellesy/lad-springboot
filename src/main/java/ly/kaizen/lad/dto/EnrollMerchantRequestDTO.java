package ly.kaizen.lad.dto;

import lombok.Data;

@Data
public class EnrollMerchantRequestDTO {
    private String name;
    private String tradeLicenseNumber;
    private String nid; // National ID
    private String address;
    private String phoneNumber;
    private String email;
    private String mccCode;
    private String iban;
    private String institutionCode;
    // Add other fields as required by the NAD API
}
