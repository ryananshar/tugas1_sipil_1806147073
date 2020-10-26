package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity 
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable {
    @Id
    @Max(20)
	@Column(name = "id_penerbangan")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPenerbangan;

    @NotNull
	@Size(max = 16)
	@Column(name = "kode_penerbangan", nullable = false, unique = true)
    private String kodePenerbangan;
    
    @NotNull
	@Size(max = 255)
	@Column(name = "kota_asal", nullable = true)
    private String kotaAsal;

    @NotNull
	@Size(max = 255)
	@Column(name = "kota_tujuan", nullable = false)
    private String kotaTujuan;

    @NotNull
    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "waktu", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuPenerbangan;
    
    @OneToMany(mappedBy = "penerbanganModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotPenerbanganModel> listPilotPenerbangan;

    public Long getIdPenerbangan() {
        return this.idPenerbangan;
    }

    public void setIdPenerbangan(Long idPenerbangan) {
        this.idPenerbangan = idPenerbangan;
    }

    public String getKodePenerbangan() {
        return this.kodePenerbangan;
    }

    public void setKodePenerbangan(String kodePenerbangan) {
        this.kodePenerbangan = kodePenerbangan;
    }

    public String getKotaAsal() {
        return this.kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getKotaTujuan() {
        return this.kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public LocalDateTime getWaktuPenerbangan() {
        return this.waktuPenerbangan;
    }

    public void setWaktuPenerbangan(LocalDateTime waktuPenerbangan) {
        this.waktuPenerbangan = waktuPenerbangan;
    }

    public List<PilotPenerbanganModel> getListPilotPenerbangan() {
        return this.listPilotPenerbangan;
    }

    public void setListPilotPenerbangan(List<PilotPenerbanganModel> listPilotPenerbangan) {
        this.listPilotPenerbangan = listPilotPenerbangan;
    }
    
    public boolean isPresent() {
		return true;
	}
}