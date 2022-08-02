package kasir.indo.desember.kasirIndoDesember.dto;

import java.time.LocalDate;
import java.util.Map;

public class DetailTransaksi {
    private Map<String, Integer> barang;
    private String namaPembeli;
    private String noTelpon;
    private String keterangan;
    private LocalDate tglDibuat;
    private LocalDate tglBayar;
    private Integer totalBayar;
    private String jenisPembayaran;
    private Boolean lunas;

    public Map<String, Integer> getBarang() {
        return barang;
    }

    public void setBarang(Map<String, Integer> barang) {
        this.barang = barang;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public LocalDate getTglDibuat() {
        return tglDibuat;
    }

    public void setTglDibuat(LocalDate tglDibuat) {
        this.tglDibuat = tglDibuat;
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
}
