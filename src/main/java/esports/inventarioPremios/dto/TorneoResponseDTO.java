package esports.inventarioPremios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorneoResponseDTO {
    private Long torneoId;
    private String nombre;
}
