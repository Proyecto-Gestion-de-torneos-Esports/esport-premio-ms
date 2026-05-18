package esports.inventarioPremios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremioRequestDTO {
    @NotBlank(message = " El tipo de premio no puede estar vacio")
    private String tipoPremio;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;

    @NotNull(message =" El monto no puede estar vacio" )
    private Double cantidadMonto;

    @NotNull(message = " El ID del torneo debe ser mayor a 0")
    @Positive(message =  "El ID del torneo debe ser mayor a 0 ")
    private Long torneoId;

    @NotNull(message = "el campo activo es obligatorio")
    private Boolean activo;
}
