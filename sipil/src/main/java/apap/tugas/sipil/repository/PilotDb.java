package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PilotDb extends JpaRepository<PilotModel, Long>{
    Optional<PilotModel> findByIdPilot(Long idPilot);

    Optional<PilotModel> findByNomorNIP(String nomorNIP);

    Optional<PilotModel> findByNomorNIK(String nomorNIK);
    
    List<PilotModel> findAllByOrderByIdPilotAsc();
    
    List<PilotModel> findByMaskapaiModelAndAkademiModel(MaskapaiModel maskapaiModel, AkademiModel akademiModel);

    List<PilotModel> findByMaskapaiModel(MaskapaiModel maskapaiModel);
    
    List<PilotModel> findByAkademiModel(AkademiModel akademiModel);

	void deleteByIdPilot(Long idpilot);
}

