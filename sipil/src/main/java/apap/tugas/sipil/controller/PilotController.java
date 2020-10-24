package apap.tugas.sipil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.service.PilotService;

// import java.util.*;

@Controller
public class PilotController {
    @Qualifier("pilotServiceImpl")
    @Autowired
    private PilotService pilotService;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/pilot/tambah")
    public String addPilotFormPage(Model model) {
        model.addAttribute("pilot", new PilotModel());

        return "form-add-pilot";
    }

    @PostMapping("/pilot/tambah")
    public String addPilotSubmit(
        @ModelAttribute PilotModel pilot,
        Model model
    ) {
        pilotService.addPilot(pilot);
        model.addAttribute("nip", pilot.getNip());

        return "add-pilot";
    }
}
