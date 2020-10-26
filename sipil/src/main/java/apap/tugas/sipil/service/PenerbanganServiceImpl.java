package apap.tugas.sipil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.repository.PenerbanganDb;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
    }

    @Override
    public List<PenerbanganModel> getPenerbanganList() {
        return penerbanganDb.findAllByOrderByIdPenerbanganAsc();
    }

    @Override
    public PenerbanganModel getPenerbanganByIdPenerbangan(Long idPenerbangan) {
        return penerbanganDb.findByIdPenerbangan(idPenerbangan).get();
        
    }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
        return penerbangan;
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan) throws Exception {
        if (penerbangan.getListPilotPenerbangan().isEmpty()) {
            penerbanganDb.deleteByIdPenerbangan(penerbangan.getIdPenerbangan());
        } else {
            throw new Exception("Penerbangan tidak bisa dihapus karena memiliki pilot.");
        }

    }
    
}
