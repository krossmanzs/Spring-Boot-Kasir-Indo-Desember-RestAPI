package kasir.indo.desember.kasirIndoDesember.model;

import javax.persistence.*;

@Entity(name = "Pembeli")
@Table
public class Pembeli {
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
    private Long idPembeli;
    private String nama;
    private Character JK;
    private Integer noTelpon;
    private String alamat;

    public Pembeli() {
    }

    public Pembeli(String nama, Character JK, Integer noTelpon, String alamat) {
        this.nama = nama;
        this.JK = JK;
        this.noTelpon = noTelpon;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Character getJK() {
        return JK;
    }

    public void setJK(Character JK) {
        this.JK = JK;
    }

    public Integer getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(Integer noTelpon) {
        this.noTelpon = noTelpon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "Pembeli{" +
                "idPembeli=" + idPembeli +
                ", nama='" + nama + '\'' +
                ", JK=" + JK +
                ", noTelpon=" + noTelpon +
                ", alamat='" + alamat + '\'' +
                '}';
    }
}
