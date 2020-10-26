package apap.tugas.sipil.controller;

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

// import java.util.*;

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
    private String home() {
        return "home";
    }

    @RequestMapping("/pilot")
    public String listPilot(Model model) {
        List<PilotModel> listPilot = pilotService.getPilotList();
        model.addAttribute("listPilot", listPilot);
        return "list-pilot";
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

    @GetMapping({"/pilot/{nomorNIP}", "/pilot/no-nip"})
    public String viewDetailResep(
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
}
