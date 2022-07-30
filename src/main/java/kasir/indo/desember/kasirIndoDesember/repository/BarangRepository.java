package kasir.indo.desember.kasirIndoDesember.repository;

import kasir.indo.desember.kasirIndoDesember.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Long> {
}
