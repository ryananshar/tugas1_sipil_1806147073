package apap.tugas.sipil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PilotDb;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    PilotDb pilotDb;

    @Override
    public void addPilot(PilotModel pilot) {
        pilotDb.save(pilot);
    }

    @Override
    public List<PilotModel> getPilotList() {
        return pilotDb.findAll();
        // return pilotDb.findAllByOrderByIdPilotAsc();
    }

    @Override
    public PilotModel getPilotByIdPilot(Long idPilot) {
        return pilotDb.findByIdPilot(idPilot).get();
    }

    @Override
    public PilotModel updatePilot(PilotModel pilot) {
        return null;
    }

    @Override
    public void deletePilot(PilotModel pilot) throws Exception {
        if (pilot.getListPilotPenerbangan().isEmpty()) {
            pilotDb.deleteById(pilot.getIdPilot());
        } else {
            throw new Exception("Pilot tidak bisa dihapus karena masih memiliki penerbangan.");
        }

    }
    
}
