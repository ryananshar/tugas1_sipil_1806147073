package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity 
@Table(name = "akademi")
public class AkademiModel implements Serializable {
	@Id
	@NotNull
    @Size(max = 20)
	@Column(name = "id_akademi")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAkademi;
    
    @NotNull
	@Size(max = 255)
	@Column(name = "nama_akademi", nullable = false)
	private String namaAkademi;

	@NotNull
	@Size(max = 255)
	@Column(name = "lokasi_akademi", nullable = false)
    private String lokasiAkademi;
    
    @OneToMany(mappedBy = "akademiModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PilotModel> listPilot;


	public Long getIdAkademi() {
		return this.idAkademi;
	}

	public void setIdAkademi(Long idAkademi) {
		this.idAkademi = idAkademi;
	}

	public String getNamaAkademi() {
		return this.namaAkademi;
	}

	public void setNamaAkademi(String namaAkademi) {
		this.namaAkademi = namaAkademi;
	}

	public String getLokasiAkademi() {
		return this.lokasiAkademi;
	}

	public void setLokasiAkademi(String lokasiAkademi) {
		this.lokasiAkademi = lokasiAkademi;
	}

	public List<PilotModel> getListPilot() {
		return this.listPilot;
	}

	public void setListPilot(List<PilotModel> listPilot) {
		this.listPilot = listPilot;
	}

	public boolean isPresent() {
		return true;
	}
}