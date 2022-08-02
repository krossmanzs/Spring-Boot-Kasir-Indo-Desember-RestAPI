package kasir.indo.desember.kasirIndoDesember.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity(name = "Barang")
@Table
public class Barang {
    @Id
    @SequenceGenerator(
            name = "barang_sequence",
            sequenceName = "barang_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "barang_sequence"
    )
    private Long idBarang;
    private String namaBarang;
    @Min(1000)
    private Integer harga;
    @Min(1)
    private Integer stok;

    public Barang() {
    }

    public Barang(String namaBarang, Integer harga, Integer stok) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }

    public Long getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Long idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "idBarang=" + idBarang +
                ", namaBarang='" + namaBarang + '\'' +
                ", harga=" + harga +
                ", stok=" + stok +
                '}';
    }
}
