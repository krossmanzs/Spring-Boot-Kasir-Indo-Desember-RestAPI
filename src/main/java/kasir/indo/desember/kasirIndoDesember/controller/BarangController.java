package kasir.indo.desember.kasirIndoDesember.controller;

import kasir.indo.desember.kasirIndoDesember.model.Barang;
import kasir.indo.desember.kasirIndoDesember.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/barang")
public class BarangController {
    @Autowired
    private BarangService barangService;

    @GetMapping
    public List<Barang> getAllBarang() {
        return barangService.getAllBarang();
    }

    @PostMapping(path = "/tambah")
    public void addBarang(@RequestBody Barang barang) {
        barangService.addNewBarang(barang);
    }

    @DeleteMapping(path = "/hapus/{id_barang}")
    public void deleteBarang(@PathVariable("id_barang") Long idBarang) {
        barangService.deleteBarang(idBarang);
    }

    @PutMapping(path = "/update/{id_barang}")
    public void updateBarang(
            @PathVariable("id_barang") Long idBarang,
            @RequestParam(required = false) String namaBarang,
            @RequestParam(required = false) Integer harga,
            @RequestParam(required = false) Integer stok
    ) {
        barangService.updateBarang(idBarang, namaBarang, harga, stok);
    }
}
