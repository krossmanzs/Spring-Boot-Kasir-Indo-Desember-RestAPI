package kasir.indo.desember.kasirIndoDesember.service;

import kasir.indo.desember.kasirIndoDesember.model.Barang;

import javax.transaction.Transactional;
import java.util.List;

public interface BarangService {

    public List<Barang> getAllBarang();

    public void addNewBarang(Barang barang);

    public void deleteBarang(Long idBarang);

    @Transactional
    public void updateBarang(Long idBarang, String nama, Integer harga, Integer stok);
}
