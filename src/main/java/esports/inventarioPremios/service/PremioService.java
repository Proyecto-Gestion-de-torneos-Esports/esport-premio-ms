package esports.inventarioPremios.service;

import esports.inventarioPremios.dto.PremioRequestDTO;
import esports.inventarioPremios.dto.PremioResponseDTO;
import esports.inventarioPremios.model.Premio;
import esports.inventarioPremios.repository.PremioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PremioService {

    private final PremioRepository premioRepository;


    private PremioResponseDTO mapToDTO(Premio prem){
        return new PremioResponseDTO(
                prem.getId(),
                prem.getTipoPremio(),
                prem.getDescripcion(),
                prem.getCantidadMonto(),
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
                () ->log.warn("No se encontro ningun premio con el id {}", id)
        );
        return resultado;
    }
    @Transactional(readOnly = true)
    public PremioResponseDTO guardar (PremioRequestDTO dto){
        Premio premio = new Premio(
                null,
                dto.getTipoPremio(),
                dto.getDescripcion(),
                dto.getCantidadMonto(),
                true
        );
        Premio premio1 = premioRepository.save(premio);
        log.info("premio guardado correctamente ID: '{}'", premio1.getId());
        return mapToDTO(premio1);

    }
    @Transactional(readOnly = true)
    public Optional <PremioResponseDTO> actualizar(Long id, PremioRequestDTO dto){
        return premioRepository.findById(id).map(existente ->{
            log.info("Premio con el ID: '{}' fue encontrado . Actualizando sus valores",id);

        })
    }
}
