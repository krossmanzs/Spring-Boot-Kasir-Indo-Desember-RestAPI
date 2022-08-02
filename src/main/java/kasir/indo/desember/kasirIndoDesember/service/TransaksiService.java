package kasir.indo.desember.kasirIndoDesember.service;

import kasir.indo.desember.kasirIndoDesember.model.Transaksi;
import kasir.indo.desember.kasirIndoDesember.model.dto.DetailTransaksi;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TransaksiService {

    public List<Transaksi> getAllTransaksi();

    public DetailTransaksi getTransaksi(Long idTransaksi);

    public void makeTransaksi(
            Long idPembeli,
            Map<Long, Integer> idBarang,
            String jenisPembayaran,
            String keterangan
    );

    public void cancelTransaksi(Long idTransaksi);
}
