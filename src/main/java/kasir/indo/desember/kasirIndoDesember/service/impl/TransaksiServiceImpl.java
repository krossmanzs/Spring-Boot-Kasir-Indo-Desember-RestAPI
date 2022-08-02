package kasir.indo.desember.kasirIndoDesember.service.impl;

import kasir.indo.desember.kasirIndoDesember.exception.BadRequestException;
import kasir.indo.desember.kasirIndoDesember.exception.ResourceNotFoundException;
import kasir.indo.desember.kasirIndoDesember.model.Barang;
import kasir.indo.desember.kasirIndoDesember.model.Pembayaran;
import kasir.indo.desember.kasirIndoDesember.model.Pembeli;
import kasir.indo.desember.kasirIndoDesember.model.Transaksi;
import kasir.indo.desember.kasirIndoDesember.dto.DetailTransaksi;
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
    public DetailTransaksi getTransaksi(Long idTransaksi) {
        Transaksi transaksi = transaksiRepository.findById(idTransaksi).orElseThrow(() -> {
            throw new ResourceNotFoundException("Id transaksi tidak ditemukan!");
        });

        Pembeli pembeli = pembeliRepository.findById(transaksi.getIdPembeli()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Id pembeli tidak ditemukan!");
        });

        Pembayaran pembayaran = pembayaranRepository.findByIdTransaksi(transaksi.getIdTransaksi()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Id pembayaran tidak ditemukan!");
        });

        DetailTransaksi detailTransaksi = new DetailTransaksi();
        // get barang dan totalnya
        Map<String, Integer> mapBarang = new HashMap<>();
        for (Map.Entry<Long, Integer> entryBarang : transaksi.getKeranjang().entrySet()) {
            Barang barang = barangRepository.findById(entryBarang.getKey()).orElseThrow(() -> {
                throw new ResourceNotFoundException("Barang dengan id: " + entryBarang.getKey() + " tidak ditemukan!");
            });

            mapBarang.put(barang.getNamaBarang(), entryBarang.getValue());
        }

        detailTransaksi.setBarang(mapBarang);
        detailTransaksi.setNamaPembeli(pembeli.getNama());
        detailTransaksi.setNoTelpon(pembeli.getNoTelpon());
        detailTransaksi.setKeterangan(transaksi.getKeterangan());
        detailTransaksi.setTglDibuat(pembayaran.getTglDibuat());
        detailTransaksi.setTglBayar(pembayaran.getTglBayar());
        detailTransaksi.setTotalBayar(pembayaran.getTotalBayar());
        detailTransaksi.setJenisPembayaran(pembayaran.getJenisPembayaran());
        detailTransaksi.setLunas(pembayaran.getLunas());

        return detailTransaksi;
    }

    @Override
    public void makeTransaksi(Long idPembeli,
                              Map<Long, Integer> keranjang,
                              String jenisPembayaran,
                              String keterangan) {
        Transaksi transaksi = new Transaksi(keranjang, idPembeli, keterangan);

        // verification for id_transaksi/id_barang/id_pembeli
        pembeliRepository.findById(transaksi.getIdPembeli()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Id pembeli tidak ditemukan!");
        });

        for (Map.Entry<Long, Integer> entryBarang : keranjang.entrySet()) {
            Barang barang = barangRepository.findById(entryBarang.getKey()).orElseThrow(() -> {
                throw new ResourceNotFoundException("Barang dengan id: " + entryBarang.getKey() + " tidak ditemukan!");
            });
        }

        if (pembayaranValid(jenisPembayaran)) {
            transaksiRepository.save(transaksi);

            pembayaranService.makePembayaran(
                    transaksiRepository.getNextValTransaksiSequence(),
                    jenisPembayaran,
                    transaksi
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
            throw new BadRequestException("Jenis pembayaran harus berjenis Tunai atau Transfer!");
        }
    }
}
