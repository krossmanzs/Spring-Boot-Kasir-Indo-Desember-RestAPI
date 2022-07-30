package kasir.indo.desember.kasirIndoDesember.controller;

import kasir.indo.desember.kasirIndoDesember.model.Pembayaran;
import kasir.indo.desember.kasirIndoDesember.service.PembayaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/pembayaran")
public class PembayaranController {

    @Autowired
    private PembayaranService pembayaranService;

    @GetMapping
    public List<Pembayaran> getAllPembayaran() {
        return pembayaranService.getAllPembayaran();
    }

    @GetMapping(path = "/{id_pembayaran}")
    public Pembayaran getPembayaran(@PathVariable("id_pembayaran") Long idPembayaran) {
        return pembayaranService.getPembayaran(idPembayaran);
    }

    @PostMapping(path = "/{id_transaksi}/{jenis_pembayaran}")
    public void makePembayaran(
            @PathVariable("id_transaksi") Long idTransaksi,
            @PathVariable("jenis_pembayaran") String jenisPembayaran
    ) {
        pembayaranService.makePembayaran(idTransaksi, jenisPembayaran);
    }

    @PutMapping(path = "/lunas/{id_pembayaran}")
    public void setLunasPembayaran(@PathVariable("id_pembayaran") Long idPembayaran) {
        pembayaranService.setLunasPembayaran(idPembayaran);
    }

    @PutMapping(path = "/update/{id_pembayaran}/{jenis_pembayaran}")
    public void updateJenisPembayaran(
            @PathVariable("id_pembayaran") Long idPembayaran,
            @PathVariable("jenis_pembayaran") String jenisPembayaran
    ) {
        pembayaranService.updateJenisPembayaran(idPembayaran, jenisPembayaran);
    }

}
