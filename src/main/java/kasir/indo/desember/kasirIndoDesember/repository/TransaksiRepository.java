package kasir.indo.desember.kasirIndoDesember.repository;

import kasir.indo.desember.kasirIndoDesember.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    @Query(value = "SELECT currval('transaksi_sequence')", nativeQuery = true)
    public Long getNextValTransaksiSequence();
}
