package apap.tugas.sipil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.repository.AkademiDb;

@Service
@Transactional
public class AkademiServiceImpl implements AkademiService {
    @Autowired
    AkademiDb akademiDb;

    @Override
    public List<AkademiModel> getAkademiList() {
        return akademiDb.findAll();
    }

    @Override
    public AkademiModel getAkademiById(Long idAkademi) {
        return akademiDb.findByIdAkademi(idAkademi).get();
    }
    
}
