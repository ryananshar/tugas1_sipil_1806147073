package apap.tugas.sipil.service;

import apap.tugas.sipil.model.PilotPenerbanganModel;

public interface PilotPenerbanganService {
    //  Method untuk menambah objek
    void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan);

    //  Method untuk mendapatkan data berdasarkan id
    PilotPenerbanganModel getByIdPilotPenerbangan(Long idPilotPenerbangan);

    // Method untuk update 
    PilotPenerbanganModel updatePilotPenerbangan(PilotPenerbanganModel pilotPenerbangan);

    // Method untuk delete 
    void deletePilotPenerbangan(Long pilotPenerbangan);
}
