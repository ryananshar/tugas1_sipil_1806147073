package apap.tugas.sipil.service;

import java.util.List;

import apap.tugas.sipil.model.PenerbanganModel;

public interface PenerbanganService {
    //  Method untuk menambah objek
    void addPenerbangan(PenerbanganModel penerbangan);

    //  Method untuk mendapatkan list data yang telah tersimpan
    List<PenerbanganModel> getPenerbanganList();

    //  Method untuk mendapatkan data berdasarkan id
    PenerbanganModel getPenerbanganByIdPenerbangan(Long idPenerbangan);

    // Method untuk update 
    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);

    // Method untuk delete 
    void deletePenerbangan(PenerbanganModel penerbangan) throws Exception;
}
