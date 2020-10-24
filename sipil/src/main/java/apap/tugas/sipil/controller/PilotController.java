package apap.tugas.sipil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;
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
        model.addAttribute("listResep", listPilot);
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
        String nip = pilot.getNip();
        pilotService.addPilot(pilot);
        model.addAttribute("nip", nip);

        return "add-pilot";
    }
}
