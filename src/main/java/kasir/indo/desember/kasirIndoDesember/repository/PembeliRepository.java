package kasir.indo.desember.kasirIndoDesember.repository;

import kasir.indo.desember.kasirIndoDesember.model.Pembeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembeliRepository extends JpaRepository<Pembeli, Long> {
}
