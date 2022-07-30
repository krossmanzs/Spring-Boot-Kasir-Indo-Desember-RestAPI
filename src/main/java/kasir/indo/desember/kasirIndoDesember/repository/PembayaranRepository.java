package kasir.indo.desember.kasirIndoDesember.repository;

import kasir.indo.desember.kasirIndoDesember.model.Pembayaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long> {
    Optional<Pembayaran> findByIdTransaksi(Long idTransaksi);
}
