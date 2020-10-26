package apap.tugas.sipil.service;

import java.util.List;

import apap.tugas.sipil.model.PilotModel;


public interface PilotService {
    //  Method untuk menambah objek
    void addPilot(PilotModel pilot);

    //  Method untuk mendapatkan semua data yang telah tersimpan
    List<PilotModel> getPilotList();

    //  Method untuk mendapatkan list data berdasarkan id
    PilotModel getPilotByIdPilot(Long idpilot);

    //  Method untuk mendapatkan list data berdasarkan NIP
    PilotModel getPilotBynomorNIP(String nomorNIP);

    //  Method untuk mendapatkan list data berdasarkan NIK
    PilotModel getPilotBynomorNIK(String nomorNIK);

    // Method untuk update 
    PilotModel updatePilot(PilotModel pilot);

    // Method untuk delete 
    void deletePilot(PilotModel pilot) throws Exception;
}
