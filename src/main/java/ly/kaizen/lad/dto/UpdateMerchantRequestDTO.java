package ly.kaizen.lad.dto;

import lombok.Data;

@Data
public class UpdateMerchantRequestDTO {
    private String schema;
    private String id; // ID or alias to identify the merchant
    private String name;
    private String tradeLicenseNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private String mccCode;
    private String iban;
    private String nid; // National ID
    // Add other fields as required by the NAD API
}
