package esports.inventarioPremios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremioResponseDTO {
    private long id;
    private String tipoPremio;
    private String descripcion;
    private Integer cantidadMonto;
    private boolean activo;
}
