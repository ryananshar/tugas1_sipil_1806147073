package apap.tugas.sipil.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PilotDb;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    PilotDb pilotDb;

    @Override
    public void addPilot(PilotModel pilot) {
        String nip = nipGenerator(pilot);
        pilot.setNomorNIP(nip);
        pilotDb.save(pilot);
    }

    @Override
    public List<PilotModel> getPilotList() {
        // return pilotDb.findAll();
        return pilotDb.findAllByOrderByIdPilotAsc();
    }

    @Override
    public PilotModel getPilotByIdPilot(Long idPilot) {
        return pilotDb.findByIdPilot(idPilot).get();
    }

    @Override
    public PilotModel updatePilot(PilotModel pilot) {
        String nip = nipGenerator(pilot);
        pilot.setNomorNIP(nip);
        pilotDb.save(pilot);
        return pilot;
    }

    @Override
    public void deletePilot(Long idpilot) {
        pilotDb.deleteByIdPilot(idpilot);
    }

    @Override
    public PilotModel getPilotBynomorNIP(String nomorNIP) {
        return pilotDb.findByNomorNIP(nomorNIP).get();
    }

    @Override
    public PilotModel getPilotBynomorNIK(String nomorNIK) {
        return pilotDb.findByNomorNIK(nomorNIK).get();
    }

    // [0]      1 jenis kelamin (Laki-laki = 1, Perempuan = 2).
    // [1-2]    2 huruf awal tempat lahir dalam kapital.
    // [3]      1 karakter terakhir nama pilot.
    // [4-7]    4 tanggal (ddmm).
    // [8-10]   3 tahun lahir kemudian dibagi 10 dan dibulatkan ke bawah.
    // [11-12]  2 huruf kapital random
    private String nipGenerator(PilotModel pilot) {
        StringBuilder builder = new StringBuilder();

        String gender = String.valueOf(pilot.getJenisKelamin());
        String tmptLahir = pilot.getTempatLahir().substring(0, 2);
        builder.append(gender);
        builder.append(tmptLahir.toUpperCase());

        String lastTwoCharName = pilot.getNamaPilot().substring(pilot.getNamaPilot().length()-1).toUpperCase();
        builder.append(lastTwoCharName);

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String formattedString = formatter.format(pilot.getTanggalLahir());
        String dateMonth = formattedString.substring(0, 4);
        Double year = Double.parseDouble(formattedString.substring(4, 8));
        Integer yearID = (int) (Math.floor(year/10));
        builder.append(dateMonth);
        builder.append(String.valueOf(yearID));

        String randomChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        while (builder.length() < 13) {
            int i = random.nextInt((25 - 0) + 1) + 0;
            builder.append(randomChar.charAt(i));
        }
        return builder.toString();
    }

    @Override
    public List<PilotModel> getPilotListByMaskapai(MaskapaiModel maskapaiModel) {
        return pilotDb.findByMaskapaiModel(maskapaiModel);
    }

    @Override
    public List<PilotModel> getPilotListByAkademi(AkademiModel akademiModel){
        return pilotDb.findByAkademiModel(akademiModel);
    }

    @Override
    public List<PilotModel> getPilotListByMaskapaiAndAkademi(MaskapaiModel maskapaiModel, AkademiModel akademiModel) {
        return pilotDb.findByMaskapaiModelAndAkademiModel(maskapaiModel, akademiModel);
    }

    @Override
    public List<PilotModel> getTop3Pilot(MaskapaiModel maskapaiModel) {
        return pilotDb.findTop3ByIdPilot(maskapaiModel);
    }

    @Override
    public List<PilotModel> getPilotThisMonth(Date lastMonth, Date thisMonth) {
        return pilotDb.findPilotByThisMonth(lastMonth, thisMonth);
    }
}
