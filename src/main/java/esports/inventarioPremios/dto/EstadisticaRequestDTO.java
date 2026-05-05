package esports.inventarioPremios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaRequestDTO {
    @NotBlank(message = " El tipo de premio no puede estar vacio")
    private String tipoPremio;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;

    @NotNull(message =" El monto no puede estar vacio" )
    private Integer cantidadMonto;

    private Boolean activo;
}
