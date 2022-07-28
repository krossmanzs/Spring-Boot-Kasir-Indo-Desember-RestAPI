package kasir.indo.desember.kasirIndoDesember.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Pembayaran")
@Table
public class Pembayaran {
    @Id
    @SequenceGenerator(
            name = "pembayaran_sequence",
            sequenceName = "pembayaran_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pembayaran_sequence"
    )
    private Long idPembayaran;
    private LocalDate tglBayar;
    private Integer totalBayar;
    private Long idTransaksi;

    public Pembayaran() {
    }

    public Pembayaran(LocalDate tglBayar, Integer totalBayar, Long idTransaksi) {
        this.tglBayar = tglBayar;
        this.totalBayar = totalBayar;
        this.idTransaksi = idTransaksi;
    }

    public Long getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Long idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public LocalDate getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(LocalDate tglBayar) {
        this.tglBayar = tglBayar;
    }

    public Integer getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(Integer totalBayar) {
        this.totalBayar = totalBayar;
    }

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    @Override
    public String toString() {
        return "Pembayaran{" +
                "idPembayaran=" + idPembayaran +
                ", tglBayar=" + tglBayar +
                ", totalBayar=" + totalBayar +
                ", idTransaksi=" + idTransaksi +
                '}';
    }
}
