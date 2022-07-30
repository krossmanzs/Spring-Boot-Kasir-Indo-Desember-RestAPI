package kasir.indo.desember.kasirIndoDesember.controller;

import kasir.indo.desember.kasirIndoDesember.model.Pembeli;
import kasir.indo.desember.kasirIndoDesember.service.PembeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/pembeli")
public class PembeliController {
    @Autowired
    private PembeliService pembeliService;

    @GetMapping
    public List<Pembeli> getAllPembeli() {
        return pembeliService.getAllPembeli();
    }

    @GetMapping(path = "/{id_pembeli}")
    public Pembeli getPembeli(@PathVariable("id_pembeli") Long idPembeli) {
        return pembeliService.getPembeli(idPembeli);
    }

    @PostMapping(path = "/tambah")
    public void addPembeli(@RequestBody Pembeli pembeli) {
        pembeliService.addPembeli(pembeli);
    }

    @DeleteMapping(path = "/hapus/{id_pembeli}")
    public void hapusPembeli(@PathVariable("id_pembeli") Long idPembeli) {
        pembeliService.deletePembeli(idPembeli);
    }

    @PutMapping(path = "/update/{id_pembeli}")
    public void updatePembeli(
            @PathVariable("id_pembeli") Long idPembeli,
            @RequestParam(required = false) String nama,
            @RequestParam(required = false) Character jk,
            @RequestParam(required = false) String noTelp,
            @RequestParam(required = false) String alamat
    ) {
        pembeliService.updatePembeli(idPembeli, nama, jk, noTelp, alamat);
    }

}
