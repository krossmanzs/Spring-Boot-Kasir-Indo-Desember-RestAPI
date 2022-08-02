package kasir.indo.desember.kasirIndoDesember.controller;

import kasir.indo.desember.kasirIndoDesember.model.Transaksi;
import kasir.indo.desember.kasirIndoDesember.model.dto.Cart;
import kasir.indo.desember.kasirIndoDesember.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    @GetMapping
    public List<Transaksi> getAllTransaksi(){
        return transaksiService.getAllTransaksi();
    }

    @GetMapping(path = "/{id_transaksi}")
    public Transaksi getTransaksi(@PathVariable("id_transaksi") Long idTransaksi) {
        return transaksiService.getTransaksi(idTransaksi);
    }

    @PostMapping(path = "/buat/{id_pembeli}")
    public void makeTransaksi(
            @PathVariable("id_pembeli") Long idPembeli,
            @RequestBody Cart cart
    ) {
        transaksiService.makeTransaksi(
                idPembeli,
                cart.getKeranjang(),
                cart.getJenisPembayaran(),
                cart.getKeterangan()
        );
    }

    @DeleteMapping(path = "/cancel/{id_transaksi}")
    public void cancelTransaksi(@PathVariable("id_transaksi") Long idTransaksi) {
        transaksiService.cancelTransaksi(idTransaksi);
    }

}
