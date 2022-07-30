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
    private LocalDate tglDibuat;
    private Integer totalBayar;
    private Long idTransaksi;
    private String jenisPembayaran;
    private Boolean lunas;

    public Pembayaran() {
        this.tglDibuat = LocalDate.now();
        this.tglBayar = null;
        this.lunas = false;
    }

    public Pembayaran(LocalDate tglBayar, LocalDate tglDibuat, Integer totalBayar, Long idTransaksi, String jenisPembayaran, Boolean lunas) {
        this.tglBayar = tglBayar;
        this.tglDibuat = tglDibuat;
        this.totalBayar = totalBayar;
        this.idTransaksi = idTransaksi;
        this.jenisPembayaran = jenisPembayaran;
        this.lunas = lunas;
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

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public Boolean getLunas() {
        return lunas;
    }

    public void setLunas(Boolean lunas) {
        this.lunas = lunas;
    }

    public LocalDate getTglDibuat() {
        return tglDibuat;
    }

    public void setTglDibuat(LocalDate tglDibuat) {
        this.tglDibuat = tglDibuat;
    }

    @Override
    public String toString() {
        return "Pembayaran{" +
                "idPembayaran=" + idPembayaran +
                ", tglBayar=" + tglBayar +
                ", tglDibuat=" + tglDibuat +
                ", totalBayar=" + totalBayar +
                ", idTransaksi=" + idTransaksi +
                ", jenisPembayaran='" + jenisPembayaran + '\'' +
                ", lunas=" + lunas +
                '}';
    }
}
