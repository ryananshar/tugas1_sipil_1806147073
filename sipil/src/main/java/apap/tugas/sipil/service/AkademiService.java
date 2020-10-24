package apap.tugas.sipil.service;

import apap.tugas.sipil.model.AkademiModel;
import java.util.List;


public interface AkademiService {
    //  Method untuk mendapatkan semua data yang telah tersimpan
    List<AkademiModel> getAkademiList();

    //  Method untuk mendapatkan list data berdasarkan id
    AkademiModel getAkademiById(Long idAkademi);
}
