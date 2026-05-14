package esports.inventarioPremios.config;

import esports.inventarioPremios.model.Premio;
import esports.inventarioPremios.repository.PremioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PremioRepository premioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Solo inserta si la tabla está completamente vacía
        if (premioRepository.count() == 0) {
            log.info(">>> DataInitializer: Base de datos de premios vacía. Insertando inventario inicial...");Premio premio1 = new Premio(null, "Efectivo", "Premio al primer lugar del torneo de Valorant", 10000, true);
            Premio premio2 = new Premio(null, "Hardware", "Teclado mecánico RGB para el MVP", 5, true);
            Premio premio3 = new Premio(null, "Trofeo", "Copa dorada de campeonato clausura", 1, true);



            premioRepository.saveAll(List.of(premio1, premio2, premio3));

            log.info(">>> DataInitializer: Inventario de premios cargado exitosamente");
        } else {
            log.info(">>> DataInitializer: La base de datos ya tiene premios. Saltando inicialización.");
        }
    }

}
