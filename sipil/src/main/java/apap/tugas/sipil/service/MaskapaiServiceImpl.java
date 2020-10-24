package apap.tugas.sipil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.repository.MaskapaiDb;

@Service
@Transactional
public class MaskapaiServiceImpl implements MaskapaiService {
    @Autowired
    MaskapaiDb maskapaiDb;

    @Override
    public List<MaskapaiModel> getMaskapaiList() {
        return maskapaiDb.findAll();
    }

    @Override
    public MaskapaiModel getMaskapaiById(Long idMaskapai) {
        return maskapaiDb.findByIdMaskapai(idMaskapai).get();
    }
    
}
