package esports.inventarioPremios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventario_premios")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "premio_id")
    private Long premioId;

    @Column(name = "tipo_premio", nullable = false, length = 50)
    private String tipoPremio;

    @Column(length = 255 )
    private String descripcion;

    @Column(name = "cantidad_monto", nullable = false)
    private Double cantidadMonto;

    @Column(name = "torneo_id")
    private Long torneoId;

    @Column(nullable = false)
    private Boolean activo = true;


}
