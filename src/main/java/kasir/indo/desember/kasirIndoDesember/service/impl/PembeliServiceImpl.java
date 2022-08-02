package kasir.indo.desember.kasirIndoDesember.service.impl;

import kasir.indo.desember.kasirIndoDesember.exception.BadRequestException;
import kasir.indo.desember.kasirIndoDesember.exception.ConflictException;
import kasir.indo.desember.kasirIndoDesember.exception.ResourceNotFoundException;
import kasir.indo.desember.kasirIndoDesember.model.Pembeli;
import kasir.indo.desember.kasirIndoDesember.repository.PembeliRepository;
import kasir.indo.desember.kasirIndoDesember.service.PembeliService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PembeliServiceImpl implements PembeliService {
    @Autowired
    private PembeliRepository pembeliRepository;

    @Override
    public List<Pembeli> getAllPembeli() {
        return pembeliRepository.findAll();
    }

    @Override
    public Pembeli getPembeli(Long idPembeli) {
        return pembeliRepository.findById(idPembeli).orElseThrow(
                () -> new ResourceNotFoundException("Pembeli dengan id " + idPembeli + " tidak ditemukan!")
        );
    }

    @Override
    public void addPembeli(Pembeli pembeli) {
        System.out.println(pembeli.toString());

        Character jk = Character.toLowerCase(pembeli.getJK());
        String noTelp = pembeli.getNoTelpon();
        System.out.println(noTelp.length());

        if (!(jk.equals('l') || jk.equals('p'))) {
            throw new BadRequestException("JK harus bernilai Laki-laki(L) atau Perempuan(P)!");
        }

        if (noTelp.length() < 10) {
            throw new BadRequestException("No telpon tidak boleh kurang dari 10 digit!");
        }

        if (noTelp.length() > 12) {
            throw new BadRequestException("No telpon tidak boleh lebih dari 12 digit!");
        }

        if (!StringUtils.isNumeric(noTelp)) {
            throw new BadRequestException("Format nomor telpon salah!");
        }

        pembeli.setJK(Character.toUpperCase(jk));
        pembeliRepository.save(pembeli);
    }

    @Override
    public void deletePembeli(Long idPembeli) {
        boolean exist = pembeliRepository.findById(idPembeli).isPresent();

        if (!exist) {
            throw new ResourceNotFoundException("Pembeli sudah dengan id " + idPembeli + " sudah ada!");
        }

        pembeliRepository.deleteById(idPembeli);
    }

    @Override
    @Transactional
    public void updatePembeli(Long idPembeli, String nama, Character jk, String noTelp, String alamat) {
        Pembeli pembeli = pembeliRepository.findById(idPembeli).orElseThrow(() -> {
            throw new ConflictException("Pembeli dengan id " + idPembeli + " sudah ada!");
        });

        System.out.println(pembeli);

        if (nama != null &&
                nama.length() > 0) {
            pembeli.setNama(nama);
        }

        if (jk != null) {
            if (!(jk.equals('l') || jk.equals('p'))) {
                throw new BadRequestException("JK harus bernilai Laki-laki(L) atau Perempuan(P)!");
            }
            pembeli.setJK(Character.toUpperCase(jk));
        }

        if (noTelp != null &&
                noTelp.length() > 0) {

            if (noTelp.length() > 12) {
                throw new BadRequestException("No telpon tidak boleh lebih dari 12 digit!");
            }

            if (!StringUtils.isNumeric(noTelp)) {
                throw new BadRequestException("Format nomor telpon salah!");
            }

            pembeli.setNoTelpon(noTelp);
        }

        if (alamat != null &&
                alamat.length() > 0) {
            pembeli.setAlamat(alamat);
        }
    }

}
