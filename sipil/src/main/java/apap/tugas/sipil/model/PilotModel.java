package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import org.hibernate.annotations.OnDelete;
// import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pilot")
public class PilotModel implements Serializable {
    @Id
    @Max(20)
	@Column(name = "id_pilot", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilot;

    @NotNull
	@Size(max = 255)
	@Column(name = "nama_pilot", nullable = false)
    private String namaPilot;
    
    @NotNull
    @Size(max = 13)
	@Column(name = "no_nip", nullable = false, unique = true)
    private String nomorNIP;
    
    @NotNull
	@Size(max = 255)
	@Column(name = "no_nik", nullable = false)
    private String nomorNIK;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_lahir", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tanggalLahir;

    @NotNull
	@Size(max = 255)
	@Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;
    
    @NotNull
	@Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "no_akademi", referencedColumnName = "id_akademi", nullable = false)
    @JsonIgnore
    private AkademiModel akademiModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "no_maskapai", referencedColumnName = "id_maskapai", nullable = false)
    @JsonIgnore
    private MaskapaiModel maskapaiModel;

    @OneToMany(mappedBy = "pilotModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotPenerbanganModel> listPilotPenerbangan;
    


    public Long getIdPilot() {
        return this.idPilot;
    }

    public void setIdPilot(Long idPilot) {
        this.idPilot = idPilot;
    }

    public String getNamaPilot() {
        return this.namaPilot;
    }

    public void setNamaPilot(String namaPilot) {
        this.namaPilot = namaPilot;
    }

    public String getNomorNIP() {
        return this.nomorNIP;
    }

    public void setNomorNIP(String nomorNIP) {
        this.nomorNIP = nomorNIP;
    }

    public String getNomorNIK() {
        return this.nomorNIK;
    }

    public void setNomorNIK(String nomorNIK) {
        this.nomorNIK = nomorNIK;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Integer getJenisKelamin() {
        return this.jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public AkademiModel getAkademiModel() {
        return this.akademiModel;
    }

    public void setAkademiModel(AkademiModel akademiModel) {
        this.akademiModel = akademiModel;
    }

    public MaskapaiModel getMaskapaiModel() {
        return this.maskapaiModel;
    }

    public void setMaskapaiModel(MaskapaiModel maskapaiModel) {
        this.maskapaiModel = maskapaiModel;
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