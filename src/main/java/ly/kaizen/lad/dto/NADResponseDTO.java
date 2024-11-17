package ly.kaizen.lad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NADResponseDTO {
    private String status; // e.g., "success", "error"
    private String message; // Optional message
    private Object data; // Flexible to hold individual or merchant data
}