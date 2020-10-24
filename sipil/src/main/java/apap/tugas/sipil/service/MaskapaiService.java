package apap.tugas.sipil.service;

import java.util.List;

import apap.tugas.sipil.model.MaskapaiModel;

public interface MaskapaiService {
    //  Method untuk mendapatkan semua data yang telah tersimpan
    List<MaskapaiModel> getMaskapaiList();

    //  Method untuk mendapatkan list data berdasarkan id
    MaskapaiModel getMaskapaiById(Long idMaskapai);
}