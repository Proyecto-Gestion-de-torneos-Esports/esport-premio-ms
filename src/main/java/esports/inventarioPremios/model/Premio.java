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
    private Long premio_id;

    @Column(name = "tipo_premio", nullable = false, length = 50)
    private String tipoPremio;

    @Column(length = 255 )
    private String descripcion;

    @Column(name = "cantidad_monto", nullable = false)
    private Integer cantidadMonto;

    private Long torneo_id;

    @Column(nullable = false)
    private Boolean activo = true;


}
