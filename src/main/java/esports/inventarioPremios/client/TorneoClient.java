package esports.inventarioPremios.client;

import esports.inventarioPremios.dto.TorneoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "torneo-service", url = "http://localhost:8084/api/torneos")
public interface TorneoClient {
    @GetMapping("/{id}")
    TorneoResponseDTO obtenerTorneoPorId(@PathVariable("id") Long id);
}
