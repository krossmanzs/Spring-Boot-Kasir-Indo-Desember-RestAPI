package kasir.indo.desember.kasirIndoDesember.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import java.util.Map;

public class Cart {
    @NotNull
    private Map<Long, Integer> keranjang; // id: jumlah
    private String jenisPembayaran;
    @JsonIgnoreProperties(ignoreUnknown = true) // membuat keterangan tidak wajib di adakan
    private String keterangan;

    public Map<Long, Integer> getKeranjang() {
        return keranjang;
    }

    public void setKeranjang(Map<Long, Integer> keranjang) {
        this.keranjang = keranjang;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idBarang=" + keranjang +
                ", jenisPembayaran='" + jenisPembayaran + '\'' +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}
