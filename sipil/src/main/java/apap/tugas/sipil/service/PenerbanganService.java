package apap.tugas.sipil.service;

import java.util.List;

import apap.tugas.sipil.model.PenerbanganModel;

public interface PenerbanganService {
    //  Method untuk menambah objek
    void addPenerbangan(PenerbanganModel penerbangan);

    //  Method untuk mendapatkan semua data yang telah tersimpan
    List<PenerbanganModel> getPenerbanganList();

    //  Method untuk mendapatkan list data berdasarkan id
    PenerbanganModel getPilotByIdPenerbangan(Long idPenerbangan);

    // Method untuk update 
    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);

    // Method untuk delete 
    void deletePenerbangan(PenerbanganModel penerbangan) throws Exception;
}
