package ly.kaizen.lad.dto;

import lombok.Data;

@Data
public class LookupRequestDTO {
    private String schema;
    private String id; // ID or alias for lookup
}
