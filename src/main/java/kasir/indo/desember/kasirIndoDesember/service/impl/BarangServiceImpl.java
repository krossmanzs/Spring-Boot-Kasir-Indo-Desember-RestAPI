package kasir.indo.desember.kasirIndoDesember.service.impl;

import kasir.indo.desember.kasirIndoDesember.exception.ResourceNotFoundException;
import kasir.indo.desember.kasirIndoDesember.model.Barang;
import kasir.indo.desember.kasirIndoDesember.repository.BarangRepository;
import kasir.indo.desember.kasirIndoDesember.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    private BarangRepository barangRepository;

    @Override
    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }

    @Override
    public void addNewBarang(Barang barang) {
        if (barang.getNamaBarang().isEmpty() ||
                barang.getStok() == null) {
            throw new ResourceNotFoundException("Stok " + barang.getNamaBarang() + " habis!");
        }

        barangRepository.save(barang);
    }

    @Override
    public void deleteBarang(Long idBarang) {
        Barang barang = barangRepository.findById(idBarang)
                .orElseThrow(() -> new ResourceNotFoundException(
                         String.format("Barang dengan id:%d tidak ditemukan!", idBarang)
                ));

        barangRepository.deleteById(idBarang);
    }

    @Override
    @Transactional
    public void updateBarang(Long idBarang, String nama, Integer harga, Integer stok) {
        Barang barang = barangRepository.findById(idBarang)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Barang dengan id:%d tidak ditemukan!", idBarang)
                ));

        if (nama != null && nama.length() > 0) {
            barang.setNamaBarang(nama);
        }

        if (harga != null && harga > 0) {
            barang.setHarga(harga);
        }

        if (stok != null && stok > 0) {
            barang.setStok(stok);
        }
    }
}
