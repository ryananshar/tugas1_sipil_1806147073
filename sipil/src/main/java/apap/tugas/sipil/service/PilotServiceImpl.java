package apap.tugas.sipil.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PilotDb;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    PilotDb pilotDb;

    @Override
    public void addPilot(PilotModel pilot) {
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
        return null;
    }

    @Override
    public void deletePilot(PilotModel pilot) throws Exception {
        if (pilot.getListPilotPenerbangan().isEmpty()) {
            pilotDb.deleteById(pilot.getIdPilot());
        } else {
            throw new Exception("Pilot tidak bisa dihapus karena masih memiliki penerbangan.");
        }
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
        String tmptLahir = pilot.getTempatLahir().substring(0, 1);
        builder.append(gender);
        builder.append(tmptLahir);

        int nameSize = pilot.getNamaPilot().length();
        String lastTwoCharName = pilot.getNamaPilot().substring(nameSize-1).toUpperCase();
        builder.append(lastTwoCharName);

        SimpleDateFormat formatter = new SimpleDateFormat("ddMM");
        String formattedString = formatter.format(pilot.getTanggalLahir());
        String dateMonth = formattedString.substring(0, 3);
        Double year = Double.parseDouble(formattedString.substring(4, 7));
        Integer yearID = (int) (Math.floor(year/10));
        builder.append(dateMonth);
        builder.append(yearID);

        String randomChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        while (builder.length() <= 12) {
            int i = random.nextInt((25 - 0) + 1) + 0;
            builder.append(randomChar.charAt(i));
        }
        return builder.toString();
    }

    
    
}
