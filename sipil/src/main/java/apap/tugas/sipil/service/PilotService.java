package apap.tugas.sipil.service;

import java.util.List;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;


public interface PilotService {
    //  Method untuk menambah objek
    void addPilot(PilotModel pilot);

    //  Method untuk mendapatkan data berdasarkan id
    PilotModel getPilotByIdPilot(Long idpilot);

    //  Method untuk mendapatkan data berdasarkan NIP
    PilotModel getPilotBynomorNIP(String nomorNIP);

    //  Method untuk mendapatkan data berdasarkan NIK
    PilotModel getPilotBynomorNIK(String nomorNIK);

    // Method untuk update 
    PilotModel updatePilot(PilotModel pilot);

    // Method untuk delete 
    void deletePilot(Long idpilot);

    //  Method untuk mendapatkan list data yang telah tersimpan
    List<PilotModel> getPilotList();

    //  Method untuk mendapatkan list data yang telah tersimpan
    List<PilotModel> getPilotListByMaskapai(MaskapaiModel maskapaiModel);

    //  Method untuk mendapatkan list data yang telah tersimpan
    List<PilotModel> getPilotListByAkademi(AkademiModel akademiModel);

    //  Method untuk mendapatkan list data yang telah tersimpan
    List<PilotModel> getPilotListByMaskapaiAndAkademi(MaskapaiModel maskapaiModel, AkademiModel akademiModel);

    //  Method untuk mendapatkan list data yang telah tersimpan
    List<PilotModel> getTop3Pilot(MaskapaiModel maskapaiModel);
}
