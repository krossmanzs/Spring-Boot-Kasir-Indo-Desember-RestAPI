package kasir.indo.desember.kasirIndoDesember.service.impl;

import kasir.indo.desember.kasirIndoDesember.model.Pembayaran;
import kasir.indo.desember.kasirIndoDesember.model.Transaksi;
import kasir.indo.desember.kasirIndoDesember.repository.BarangRepository;
import kasir.indo.desember.kasirIndoDesember.repository.PembayaranRepository;
import kasir.indo.desember.kasirIndoDesember.repository.PembeliRepository;
import kasir.indo.desember.kasirIndoDesember.repository.TransaksiRepository;
import kasir.indo.desember.kasirIndoDesember.service.PembayaranService;
import kasir.indo.desember.kasirIndoDesember.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    PembayaranRepository pembayaranRepository;
    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private PembeliRepository pembeliRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private PembayaranService pembayaranService;

    @Override
    public List<Transaksi> getAllTransaksi() {
        return transaksiRepository.findAll();
    }

    @Override
    public Transaksi getTransaksi(Long idTransaksi) {
        return transaksiRepository.findById(idTransaksi).orElseThrow(() -> {
            throw new IllegalStateException("Id transaksi tidak ditemukan!");
        });
    }

    @Override
    public void makeTransaksi(Long idPembeli,
                              Map<Long, Integer> keranjang,
                              String jenisPembayaran,
                              String keterangan) {
        Transaksi transaksi = new Transaksi(keranjang, idPembeli, keterangan);

        // verification for id_transaksi/id_barang/id_pembeli
        pembeliRepository.findById(transaksi.getIdPembeli()).orElseThrow(() -> {
            throw new IllegalStateException("Id pembeli tidak ditemukan!");
        });

        barangRepository.findAllById(keranjang.keySet()).forEach(barang -> {
            if (barang == null) {
                throw new IllegalStateException("Barang dengan id " + barang.getIdBarang() + " tidak ditemukan!");
            }
        });

        if (pembayaranValid(jenisPembayaran)) {
            transaksiRepository.save(transaksi);

            pembayaranService.makePembayaran(
                    transaksiRepository.getNextValTransaksiSequence(),
                    jenisPembayaran
            );
        }

    }

    @Override
    public void cancelTransaksi(Long idTransaksi) {
        transaksiRepository.deleteById(idTransaksi);
        Optional<Pembayaran> pembayaran = pembayaranRepository.findByIdTransaksi(idTransaksi);
        pembayaran.ifPresent(value -> pembayaranRepository.delete(value));
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
