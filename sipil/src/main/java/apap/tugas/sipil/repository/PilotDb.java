package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;
// import apap.tugas.sipil.model.PilotPenerbanganModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    
    @Query(
        value = 
        "SELECT p, COUNT(p.idPilot) as x " +
        "FROM PilotModel p, PilotPenerbanganModel q " +
        "WHERE q.pilotModel.idPilot = p.idPilot " +
        "AND p.maskapaiModel = (:maskapai) " +
        "GROUP BY p.idPilot"
        )
    List<PilotModel> findTop3ByIdPilot(@Param("maskapai") MaskapaiModel maskapai);

    @Query(
        value = 
        "SELECT p " +
        "FROM PilotModel p " +
        "LEFT JOIN PilotPenerbanganModel q " +
        "ON q.pilotModel.idPilot = p.idPilot " +
        "LEFT JOIN PenerbanganModel r " +
        "ON q.penerbanganModel.kodePenerbangan = r.kodePenerbangan " +
        "WHERE r.waktuPenerbangan BETWEEN (:lastMonth) AND (:thisMonth)"
        )
	List<PilotModel> findPilotByThisMonth(@Param("lastMonth") Date lastMonth, @Param("thisMonth") Date thisMonth);

}

