package kasir.indo.desember.kasirIndoDesember.service;

import kasir.indo.desember.kasirIndoDesember.model.Pembeli;

import javax.transaction.Transactional;
import java.util.List;

public interface PembeliService {

    public List<Pembeli> getAllPembeli();
    public Pembeli getPembeli(Long idPembeli);

    public void addPembeli(Pembeli pembeli);

    public void deletePembeli(Long idPembeli);

    public void updatePembeli(Long idPembeli, String nama, Character jk, String noTelp, String alamat);
}
