package esports.inventarioPremios.controller;

import esports.inventarioPremios.dto.PremioRequestDTO;
import esports.inventarioPremios.dto.PremioResponseDTO;
import esports.inventarioPremios.service.PremioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/premio")
@RequiredArgsConstructor
public class PremioController {
        private final PremioService premioService;

        @GetMapping
    public ResponseEntity<List<PremioResponseDTO>> obtenerTodas(){
            return ResponseEntity.ok(premioService.obtenertodo());
        }

        @GetMapping("{id}")
    public ResponseEntity<PremioResponseDTO> obtenerPorId(@PathVariable Long id){
            return premioService.obtenerPorId(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

       @PostMapping
       public ResponseEntity<PremioResponseDTO> guardar(@Valid @RequestBody PremioRequestDTO dto){
            PremioResponseDTO premioCreado = premioService.crearPremio(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(premioCreado);
       }

       @PutMapping("/{id}")
       public ResponseEntity<PremioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PremioRequestDTO dto){
            return premioService.actualizar(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.noContent().build());
       }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
            if (premioService.obtenerPorId(id).isEmpty()){
                return ResponseEntity.notFound().build();
            }
            premioService.eliminar(id);

            return ResponseEntity.noContent().build();
        }
}
