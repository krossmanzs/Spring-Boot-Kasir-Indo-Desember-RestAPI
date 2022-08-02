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
import java.util.Map;
import java.util.Optional;

@Service
public class PembayaranServiceImpl implements PembayaranService {

    @Autowired
    private PembayaranRepository pembayaranRepository;

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Override
    @Transactional
    public void makePembayaran(Long idTransaksi, String jenisPembayaran, Transaksi transaksi) {
        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setIdTransaksi(idTransaksi);

        // set total bayar
        int totalBayar = 0;

        for (Map.Entry<Long, Integer> entryBarang : transaksi.getKeranjang().entrySet()) {
            Barang barang = barangRepository.findById(entryBarang.getKey()).orElseThrow(() -> {
                throw new IllegalStateException("Barang dengan ID " + entryBarang.getKey() + " tidak ditemukan!");
            });

            int jumlahBeli = entryBarang.getValue();

            // cek ketersediaan barang
            if (jumlahBeli > barang.getStok()) {
                throw new IllegalStateException("Anda melebihi jumlah stok untuk barang " + barang.getNamaBarang());
            } if (barang.getStok() <= 0) {
                throw new IllegalStateException("Stok untuk barang " + barang.getNamaBarang() + " sudah habis!");
            }

            totalBayar += barang.getHarga() * jumlahBeli;
            barang.setStok(barang.getStok() - jumlahBeli);
        }

        pembayaran.setTotalBayar(totalBayar);

        // jenis pembayaran harus berjenis tunai / transfer
        if (pembayaranValid(jenisPembayaran)) {
            pembayaran.setJenisPembayaran(jenisPembayaran);
            pembayaranRepository.save(pembayaran);
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
    @Transactional
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
