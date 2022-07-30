package kasir.indo.desember.kasirIndoDesember.service.impl;

import kasir.indo.desember.kasirIndoDesember.model.Barang;
import kasir.indo.desember.kasirIndoDesember.model.Pembayaran;
import kasir.indo.desember.kasirIndoDesember.model.Transaksi;
import kasir.indo.desember.kasirIndoDesember.repository.BarangRepository;
import kasir.indo.desember.kasirIndoDesember.repository.PembayaranRepository;
import kasir.indo.desember.kasirIndoDesember.repository.TransaksiRepository;
import kasir.indo.desember.kasirIndoDesember.service.PembayaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
public class PembayaranServiceImpl implements PembayaranService {

    @Autowired
    private PembayaranRepository pembayaranRepository;

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Override
    public void makePembayaran(Long idTransaksi, String jenisPembayaran) {
        Pembayaran pembayaran = new Pembayaran();

        // set total bayar
        // TODO: 7/30/2022 buat fitur total bayar setelah program transaksi selesai
        Transaksi transaksi = transaksiRepository.getReferenceById(idTransaksi);
        List<Barang> listBarang = barangRepository.findAllById(transaksi.getIdBarang());

        Integer totalBayar = 0;
        for (Barang barang : listBarang) {
            totalBayar += barang.getHarga();
        }

        pembayaran.setTotalBayar(totalBayar);

        // jenis pembayaran harus berjenis tunai / transfer
        if (pembayaranValid(jenisPembayaran)) {
            pembayaran.setJenisPembayaran(jenisPembayaran);
        }
    }

    @Override
    public Pembayaran getPembayaran(Long idPembayaran) {
        return pembayaranRepository.findById(idPembayaran).orElseThrow(() -> {
            throw new IllegalStateException("Id pembayaran tidak ditemukan!");
        });
    }

    @Override
    public List<Pembayaran> getAllPembayaran() {
        return pembayaranRepository.findAll();
    }

    @Override
    @Transactional
    public void setLunasPembayaran(Long idPembayaran) {
        Pembayaran pembayaran = pembayaranRepository.findById(idPembayaran).orElseThrow(() -> {
            throw new IllegalStateException("Id pembayaran tidak ditemukan!");
        });

        pembayaran.setLunas(true);
    }

    @Override
    public void updateJenisPembayaran(Long idPembayaran, String jenisPembayaran) {
        Pembayaran pembayaran = pembayaranRepository.findById(idPembayaran).orElseThrow(() -> {
            throw new IllegalStateException("Id pembayaran tidak ditemukan!");
        });

        if (pembayaranValid(jenisPembayaran)) {
            pembayaran.setJenisPembayaran(jenisPembayaran);
        }
    }

    private boolean pembayaranValid(String jenisPembayaran) {
        if (jenisPembayaran.toLowerCase(Locale.ROOT).equals("tunai") ||
                jenisPembayaran.toLowerCase(Locale.ROOT).equals("transfer")) {
            return true;
        } else {
            throw new IllegalStateException("Jenis pembayaran harus berjenis Tunai atau Transfer!");
        }
    }
}
