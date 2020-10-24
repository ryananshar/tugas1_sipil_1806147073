package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity 
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable {
    @Id
    @Size(max = 20)
	@Column(name = "id_penerbangan")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPenerbangan;

    @NotNull
	@Size(max = 16)
	@Column(name = "kode_penerbangan", nullable = false, unique = true)
    private String kodePenerbangan;
    
    @NotNull
	@Size(max = 255)
	@Column(name = "kode_asal", nullable = false)
    private String kodeAsal;

    @NotNull
	@Size(max = 255)
	@Column(name = "kode_tujuan", nullable = false)
    private String kode_tujuan;

    @NotNull
    // @Temporal(TemporalType.TIMESTAMP)
	@Size(max = 255)
	@Column(name = "waktu", nullable = false)
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

    public String getKodeAsal() {
        return this.kodeAsal;
    }

    public void setKodeAsal(String kodeAsal) {
        this.kodeAsal = kodeAsal;
    }

    public String getKode_tujuan() {
        return this.kode_tujuan;
    }

    public void setKode_tujuan(String kode_tujuan) {
        this.kode_tujuan = kode_tujuan;
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