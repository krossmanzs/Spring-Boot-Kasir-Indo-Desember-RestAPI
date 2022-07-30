package kasir.indo.desember.kasirIndoDesember.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

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
    @ElementCollection(targetClass = Long.class)
    private Set<Long> idBarang;
    private Long idPembeli;
    private LocalDate tanggal;
    private String keterangan;

    public Transaksi() {
    }

    public Transaksi(Set<Long> idBarang, Long idPembeli, String keterangan) {
        this.idBarang = idBarang;
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

    public Set<Long> getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Set<Long> idBarang) {
        this.idBarang = idBarang;
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
                ", idBarang=" + idBarang +
                ", tanggal=" + tanggal +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}
