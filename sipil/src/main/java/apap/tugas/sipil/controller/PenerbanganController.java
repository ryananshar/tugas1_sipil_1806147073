package apap.tugas.sipil.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.service.PenerbanganService;
import apap.tugas.sipil.service.PilotService;

@Controller
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    PenerbanganService penerbanganService;

    @Autowired
    PilotService pilotService;

    @RequestMapping("/penerbangan")
    public String listPenerbangan(Model model) {
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "list-penerbangan";
    }

    @GetMapping({"/penerbangan/detail/{idPenerbangan}", "/penerbangan/detail/"})
    public String viewDetailPilot(
        @PathVariable(value = "nomorNIP", required = false) Long idPenerbangan,
        Model model
    ) {
        try {
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);
            List<PilotPenerbanganModel> listPilot = penerbangan.getListPilotPenerbangan(); 
            model.addAttribute("penerbangan", penerbangan);             
            model.addAttribute("listPilot", listPilot);

            return "detail-penerbangan";
        } catch (NoSuchElementException e) {
            String message = "Proses pencarian tidak dilakukan karena Id penerbangan tidak ada.";
            model.addAttribute("message", message);
            
            return "detail-penerbangan";
        }                
    }

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model) {
        model.addAttribute("penerbangan", new PenerbanganModel());
        
        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    public String addPenerbanganSubmit(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ) {
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbangan);

        return "add-penerbangan";
    }

    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    private String changePilotPage(
        @PathVariable Long idPenerbangan,
        Model model
    ) {
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);

        return "form-update-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String changePilotSubmit(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ) {
        PenerbanganModel updatedPenerbangan = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("penerbangan", updatedPenerbangan);

        return "update-penerbangan";
    }

    // @GetMapping("/penerbangan/hapus/{idPilot}")
    // public String deleteObat(
    //     @PathVariable Long idPenerbangan, 
    //     Model model
    // ) {
    //     PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);
    //     penerbanganService.deletePenerbangan(penerbangan);
    //     model.addAttribute("penerbangan", penerbangan);

    //     return "delete-penerbangan";
    // }

    @GetMapping({"/penerbangan/hapus/{idPilot}", "/penerbangan/hapus/"})
    public String deletePenerbangan(
            @PathVariable(value = "noResep", required = false) Long idPenerbangan,
            Model model
    ) throws Exception {
        if (idPenerbangan != null) {
            if (isPenerbanganExists(idPenerbangan) && !hasPilot(idPenerbangan)) {
                PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);
                penerbanganService.deletePenerbangan(penerbangan);
                model.addAttribute("penerbangan", penerbangan);

                return "delete-penerbangan";
            } else if (hasPilot(idPenerbangan)) {
                model.addAttribute("msg", "Penerbangan masih memiliki pilot! Hapus pilot terlebih dahulu!");

                return "delete-penerbangan";
            }
        }
        model.addAttribute("msg", "Nomor Resep Tidak Ditemukan atau Nomor Resep Tidak Ada!");

        return "delete-error";
    }

    private boolean hasPilot(Long idPenerbangan) {
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);
        List<PilotPenerbanganModel> listPilot = penerbangan.getListPilotPenerbangan();
        return listPilot.size() != 0;
    }

    private boolean isPenerbanganExists(Long idPenerbangan) {
        return penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan).isPresent();
    }
}
