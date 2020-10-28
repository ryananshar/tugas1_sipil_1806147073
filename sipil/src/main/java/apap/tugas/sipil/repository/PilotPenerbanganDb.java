package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.PilotPenerbanganModel;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PilotPenerbanganDb extends JpaRepository<PilotPenerbanganModel, Long>{
    Optional<PilotPenerbanganModel> findByIdPilotPenerbangan(Long idPilotPenerbangan);

    void deleteByIdPilotPenerbangan(Long pilotPenerbangan);
    
}