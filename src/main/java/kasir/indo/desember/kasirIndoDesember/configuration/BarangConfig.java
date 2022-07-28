package kasir.indo.desember.kasirIndoDesember.configuration;

import kasir.indo.desember.kasirIndoDesember.model.Barang;
import kasir.indo.desember.kasirIndoDesember.repository.BarangRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BarangConfig {

    @Bean
    CommandLineRunner commandLineRunner(BarangRepository barangRepository) {
        return args -> {
            Barang briyan = new Barang(
                    "Briyan Anti Ketombe",
                    15000,
                    5
            );

            Barang sabunColek = new Barang(
                    "Sabun Colek Papa Lemon",
                    9500,
                    3
            );

            Barang steamWallet = new Barang(
                    "Steam Wallet Rp. 12.000",
                    13500,
                    6
            );

            barangRepository.saveAll(
                    List.of(steamWallet,sabunColek,briyan)
            );
        };
    }
}
