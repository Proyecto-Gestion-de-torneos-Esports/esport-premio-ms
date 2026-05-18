package esports.inventarioPremios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremioResponseDTO {
    private long premioId;
    private String tipoPremio;
    private String descripcion;
    private Double cantidadMonto;
    private Long torneoId;
    private boolean activo;
}
