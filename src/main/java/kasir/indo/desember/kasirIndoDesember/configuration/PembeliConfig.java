package kasir.indo.desember.kasirIndoDesember.configuration;

import kasir.indo.desember.kasirIndoDesember.model.Pembeli;
import kasir.indo.desember.kasirIndoDesember.repository.PembeliRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PembeliConfig {
    @Bean
    CommandLineRunner commandLineRunner2(PembeliRepository pembeliRepository) {
        return args -> {
            Pembeli dono = new Pembeli(
                    "Dono Soedirman",
                    'P',
                    "088232424522",
                    "Jl. Mars Xaverius Bandarlampung Indomaret, Lampung"
            );

            Pembeli sukidi = new Pembeli(
                    "Sukidi Kidi",
                    'L',
                    "083242512342",
                    "Jl. Mulu Kapan Nembak No. 69"
            );

            Pembeli sukiri = new Pembeli(
                    "Sukiri Kanan",
                    'P',
                    "087423726263",
                    "Jl. Jalan Kulihat Saja No. 1945"
            );

            pembeliRepository.saveAll(
                    List.of(sukiri, sukidi, dono)
            );
        };
    }
}
