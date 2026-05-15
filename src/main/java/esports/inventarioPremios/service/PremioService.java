package esports.inventarioPremios.service;

import esports.inventarioPremios.client.AuditoriaClient;
import esports.inventarioPremios.client.TorneoClient;
import esports.inventarioPremios.dto.*;
import esports.inventarioPremios.model.Premio;
import esports.inventarioPremios.repository.PremioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PremioService {

    private final PremioRepository premioRepository;
    private final AuditoriaClient auditoriaClient;
    private final TorneoClient torneoClient;


    private PremioResponseDTO mapToDTO(Premio prem){
        return new PremioResponseDTO(
                prem.getPremio_id(),
                prem.getTipoPremio(),
                prem.getDescripcion(),
                prem.getCantidadMonto(),
                prem.getTorneo_id(),
                prem.getActivo()
        );
    }

    @Transactional(readOnly = true)
    public List<PremioResponseDTO> obtenertodo() {
        log.info("listando premios");
        List<Premio> premios = premioRepository.findAll();
        log.info("hay {} registro de premios en total ", premios.size());
        return premios.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Optional<PremioResponseDTO> obtenerPorId(Long id){
        Optional<PremioResponseDTO> resultado = premioRepository.findById(id).map(this::mapToDTO);

        resultado.ifPresentOrElse(
                dto ->log.info("Premio ID '{}' encontrado exitosamente ", id),
                () ->log.warn("No se encontro ningun premio con el ID: {}", id)
        );
        return resultado;
    }

    @Transactional
    public PremioResponseDTO crearPremio(PremioRequestDTO dto) {
        log.info("Creando nuevo premio para el torneo ID: {}", dto.getTorneo_id());

        TorneoResponseDTO torneo = torneoClient.obtenerTorneoPorId(dto.getTorneo_id());
        // Map
        Premio nuevoPremio = new Premio();
        nuevoPremio.setTipoPremio(dto.getTipoPremio());
        nuevoPremio.setDescripcion(dto.getDescripcion());
        nuevoPremio.setCantidadMonto(dto.getCantidadMonto());
        nuevoPremio.setTorneo_id(dto.getTorneo_id());

        // Guardar
        Premio premioGuardado = premioRepository.save(nuevoPremio);
        log.info("Premio '{}' registrado exitosamente con ID: {}", premioGuardado.getTipoPremio(), premioGuardado.getPremio_id());
        generarAuditoria("Se guardo premio");

        // Retornar DTO
        return new PremioResponseDTO(
                premioGuardado.getPremio_id(),
                premioGuardado.getTipoPremio(),
                premioGuardado.getDescripcion(),
                premioGuardado.getCantidadMonto(),
                premioGuardado.getTorneo_id(),
                premioGuardado.getActivo()

        );

    }
    @Transactional
    public Optional <PremioResponseDTO> actualizar(Long id, PremioRequestDTO dto){
        return premioRepository.findById(id).map(existente ->{
            log.info("Premio con el ID: '{}' fue encontrado . Actualizando sus valores",id);
            existente.setTipoPremio(dto.getTipoPremio());
            existente.setDescripcion(dto.getDescripcion());
            existente.setCantidadMonto(dto.getCantidadMonto());
            existente.setTorneo_id(dto.getTorneo_id());
            existente.setActivo(dto.getActivo());
            log.info("Premio con ID: {} actualizado correctamente", id);
            generarAuditoria("Se actualizo Premio");
            return mapToDTO(premioRepository.save(existente));
        });
    }
    @Transactional
    public void eliminar (Long id){
        log.info("procesando solicitud para eliminar ID: '{}'", id);
        premioRepository.findById(id).ifPresentOrElse(existente -> {
            premioRepository.delete(existente);
            log.info("la estadistica ID: {} fue eliminado correctamente", id);
            generarAuditoria("Se elimino Premio");
        },() ->{
            log.warn("no se encontro el ID: '{}' de premio",id);
        });
    }
    public void generarAuditoria(String detalle){
        AuditoriaRequestDTO dto = new AuditoriaRequestDTO();
        LocalDate ahora = LocalDate.now();
        dto.setDetalle(detalle);
        dto.setFecha(ahora);

        AuditoriaResponseDTO respuesta = auditoriaClient.generarAuditoria(dto);
    }


}
