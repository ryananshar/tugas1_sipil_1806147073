package apap.tugas.sipil.controller;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.service.AkademiService;
import apap.tugas.sipil.service.MaskapaiService;
import apap.tugas.sipil.service.PilotService;

@Controller
public class PilotController {
    @Qualifier("pilotServiceImpl")
    @Autowired
    PilotService pilotService;

    @Autowired
    AkademiService akademiService;

    @Autowired
    MaskapaiService maskapaiService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/cari")
    public String cari() {
        return "cari";
    }

    @GetMapping("/pilot")
    public String listPilot(Model model) {
        List<PilotModel> listPilot = pilotService.getPilotList();
        model.addAttribute("listPilot", listPilot);
        return "list-pilot";
    }

    @GetMapping({"/pilot/{nomorNIP}", "/pilot/no-nip"})
    public String viewDetailPilot(
        @PathVariable(value = "nomorNIP", required = false) String nomorNIP,
        Model model
    ) {
        try {
            PilotModel pilot = pilotService.getPilotBynomorNIP(nomorNIP);
            List<PilotPenerbanganModel> listPenerbangan = pilot.getListPilotPenerbangan(); 
            model.addAttribute("pilot", pilot);             
            model.addAttribute("listPenerbangan", listPenerbangan);

            return "detail-pilot";
        } catch (NoSuchElementException e) {
            String message = "Proses pencarian tidak dilakukan karena NIP pilot tidak ada.";
            model.addAttribute("message", message);
            
            return "detail-pilot";
        }                
    }

    @GetMapping("/pilot/tambah")
    public String addPilotFormPage(Model model) {
        List<AkademiModel> listAkademi = akademiService.getAkademiList();
        List<MaskapaiModel> listMaskapai = maskapaiService.getMaskapaiList();

        model.addAttribute("pilot", new PilotModel());
        model.addAttribute("listAkademi", listAkademi); 
        model.addAttribute("listMaskapai", listMaskapai); 
        
        return "form-add-pilot";
    }

    @PostMapping("/pilot/tambah")
    public String addPilotSubmit(
        @ModelAttribute PilotModel pilot,
        Model model
    ) {
        pilotService.addPilot(pilot);
        model.addAttribute("pilot", pilot);

        return "add-pilot";
    }

    @GetMapping("/pilot/ubah/{nomorNIP}")
    private String changePilotPage(
        @PathVariable String nomorNIP,
        Model model
    ) {
        PilotModel pilot = pilotService.getPilotBynomorNIP(nomorNIP);
        List<AkademiModel> listAkademi = akademiService.getAkademiList();
        List<MaskapaiModel> listMaskapai = maskapaiService.getMaskapaiList();

        model.addAttribute("pilot", pilot);
        model.addAttribute("listAkademi", listAkademi); 
        model.addAttribute("listMaskapai", listMaskapai);

        return "form-update-pilot";
    }

    @PostMapping("/pilot/ubah")
    public String changePilotSubmit(
        @ModelAttribute PilotModel pilot,
        Model model
    ) {
        PilotModel updatedPilot = pilotService.updatePilot(pilot);
        model.addAttribute("pilot", updatedPilot);

        return "update-pilot";
    }

    @GetMapping("/pilot/hapus/{idPilot}")
    public String deleteObat(@PathVariable Long idPilot, Model model) {
        PilotModel pilot = pilotService.getPilotByIdPilot(idPilot);
        pilotService.deletePilot(idPilot);
        model.addAttribute("pilot", pilot);
        model.addAttribute("idPilot", idPilot);

        return "delete-pilot";
    }

    @RequestMapping("/cari/pilot")
    public String cariPilotMaskapaiAkademi(
        @RequestParam(value = "kodeMaskapai", required = false) String kodeMaskapai,
        @RequestParam(value = "idAkademi", required = false) Long idAkademi,
        Model model
    ) {
        List<AkademiModel> listAkademi = akademiService.getAkademiList();
        List<MaskapaiModel> listMaskapai = maskapaiService.getMaskapaiList();
        List<PilotModel> listPilot = null;

        MaskapaiModel maskapaiModel = new MaskapaiModel();
        AkademiModel akademiModel = new AkademiModel();

        try {
            maskapaiModel = maskapaiService.getMaskapaiByKode(kodeMaskapai);
            akademiModel = akademiService.getAkademiById(idAkademi);
            listPilot = pilotService.getPilotListByMaskapaiAndAkademi(maskapaiModel, akademiModel);
        } catch (Exception e) {
            try {
                maskapaiModel = maskapaiService.getMaskapaiByKode(kodeMaskapai);
                listPilot = pilotService.getPilotListByMaskapai(maskapaiModel);
            } catch (Exception f) {
                try {
                    akademiModel = akademiService.getAkademiById(idAkademi);
                    listPilot = pilotService.getPilotListByAkademi(akademiModel);
                } catch (Exception g) {
                }
                
            }
        }
        model.addAttribute("listAkademi", listAkademi); 
        model.addAttribute("listMaskapai", listMaskapai); 
        model.addAttribute("listPilot", listPilot);
        model.addAttribute("akademiModel", akademiModel);
        model.addAttribute("maskapaiModel", maskapaiModel);

        return "cari-maskapai-akademi";
    }

    @RequestMapping("/cari/pilot/penerbangan-terbanyak")
    public String cariPilotPenerbanganTerbanyak(
        @RequestParam(value = "kodeMaskapai", required = false) String kodeMaskapai,
        Model model
    ) {
        List<MaskapaiModel> listMaskapai = maskapaiService.getMaskapaiList();
        List<PilotModel> listPilot = null;

        MaskapaiModel maskapaiModel = new MaskapaiModel();

        try {
            maskapaiModel = maskapaiService.getMaskapaiByKode(kodeMaskapai);
            listPilot = pilotService.getTop3Pilot(maskapaiModel);
            
        } catch (Exception e) {   
        }
        model.addAttribute("listMaskapai", listMaskapai); 
        model.addAttribute("listPilot", listPilot);
        model.addAttribute("maskapaiModel", maskapaiModel);

        return "cari-penerbangan-terbanyak";
    }

    @GetMapping("/cari/pilot/bulan-ini")
    public String bonus(Model model) {  
        Date now = Date.from(ZonedDateTime.now().toInstant());
        Date minusNow = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        List<PilotModel> listPilot = pilotService.getPilotThisMonth(minusNow, now);
        model.addAttribute("listPilot", listPilot);
        return "list-pilot-bulan-ini";
    }
}
