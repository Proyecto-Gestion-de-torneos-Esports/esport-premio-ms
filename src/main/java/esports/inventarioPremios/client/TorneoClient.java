package esports.inventarioPremios.client;

import esports.inventarioPremios.dto.TorneoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "torneos", url = "http://localhost:8003/api/torneos")
public interface TorneoClient {
    @GetMapping("/{id}")
    TorneoResponseDTO obtenerTorneoPorId(@PathVariable Long id);
}
