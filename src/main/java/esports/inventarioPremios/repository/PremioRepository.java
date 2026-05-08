package esports.inventarioPremios.repository;

import esports.inventarioPremios.model.Premio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PremioRepository extends JpaRepository<Premio, Long> {
  List<Premio> findByTipoPremio (String tipoPremio);



}
