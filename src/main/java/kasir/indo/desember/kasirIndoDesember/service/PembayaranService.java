package kasir.indo.desember.kasirIndoDesember.service;

import kasir.indo.desember.kasirIndoDesember.model.Pembayaran;
import kasir.indo.desember.kasirIndoDesember.model.Transaksi;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PembayaranService {
    public void makePembayaran(Long idTransaksi, String jenisPembayaran);

    public Pembayaran getPembayaran(Long idPembayaran);

    public List<Pembayaran> getAllPembayaran();

    public void setLunasPembayaran(Long idPembayaran);

    public void updateJenisPembayaran(Long idPembayaran, String jenisPembayaran);
}
