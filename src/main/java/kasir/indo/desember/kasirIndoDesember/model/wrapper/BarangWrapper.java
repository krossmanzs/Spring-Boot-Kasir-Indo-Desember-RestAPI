package kasir.indo.desember.kasirIndoDesember.model.wrapper;

import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BarangWrapper {
    @NotNull
    private Set<Long> idBarang;
    private String jenisPembayaran;
    private String keterangan;

    @Override
    public String toString() {
        return "BarangWrapper{" +
                "idBarang=" + idBarang +
                ", jenisPembayaran='" + jenisPembayaran + '\'' +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }

    public Set<Long> getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Set<Long> idBarang) {
        this.idBarang = idBarang;
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
}
