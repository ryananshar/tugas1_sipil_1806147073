package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.MaskapaiModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaskapaiDb extends JpaRepository<MaskapaiModel, Long>{
    Optional<MaskapaiModel> findByIdMaskapai(Long idMaskapai);
}
