package kasir.indo.desember.kasirIndoDesember.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Entity(name = "Transaksi")
@Table
public class Transaksi {
    @Id
    @SequenceGenerator(
            name = "transaksi_sequence",
            sequenceName = "transaksi_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaksi_sequence"
    )
    private Long idTransaksi;
    @ElementCollection
    private Map<Long, Integer> keranjang;
    private Long idPembeli;
    private LocalDate tanggal;
    private String keterangan;

    public Transaksi() {
    }

    public Transaksi(Map<Long, Integer> keranjang, Long idPembeli, String keterangan) {
        this.keranjang = keranjang;
        this.idPembeli = idPembeli;
        this.keterangan = Objects.requireNonNullElse(keterangan, "--");
        this.tanggal = LocalDate.now();
    }

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Map<Long, Integer> getKeranjang() {
        return keranjang;
    }

    public void setKeranjang(Map<Long, Integer> keranjang) {
        this.keranjang = keranjang;
    }

    public Long getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(Long idPembeli) {
        this.idPembeli = idPembeli;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "idTransaksi=" + idTransaksi +
                ", keranjang=" + keranjang +
                ", idPembeli=" + idPembeli +
                ", tanggal=" + tanggal +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}
