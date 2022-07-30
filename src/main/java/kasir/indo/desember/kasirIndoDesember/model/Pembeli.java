package kasir.indo.desember.kasirIndoDesember.model;

import javax.persistence.*;

@Entity(name = "Pembeli")
@Table
public class Pembeli {
    @Id
    @SequenceGenerator(
            name = "pembeli_sequence",
            sequenceName = "pembeli_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pembeli_sequence"
    )
    private Long idPembeli;
    private String nama;
    private Character JK;
    private String noTelpon;
    private String alamat;

    public Pembeli() {
    }

    public Pembeli(String nama, Character JK, String noTelpon, String alamat) {
        this.nama = nama;
        this.JK = JK;
        this.noTelpon = noTelpon;
        this.alamat = alamat;
    }

    public Long getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(Long idPembeli) {
        this.idPembeli = idPembeli;
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

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
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
