package apap.tugas.sipil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.repository.PilotPenerbanganDb;

@Service
@Transactional
public class PilotPenerbanganServiceImpl implements PilotPenerbanganService {
    @Autowired
    PilotPenerbanganDb ppDb;

    @Override
    public void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan) {
        ppDb.save(pilotPenerbangan);
    }

    @Override
    public PilotPenerbanganModel getByIdPilotPenerbangan(Long idPilotPenerbangan) {
        return ppDb.findByIdPilotPenerbangan(idPilotPenerbangan).get();
    }

    @Override
    public PilotPenerbanganModel updatePilotPenerbangan(PilotPenerbanganModel pilotPenerbangan) {
        ppDb.save(pilotPenerbangan);
        return pilotPenerbangan;
    }

    @Override
    public void deletePilotPenerbangan(Long pilotPenerbangan) {
        ppDb.deleteByIdPilotPenerbangan(pilotPenerbangan);

    }
    
}
