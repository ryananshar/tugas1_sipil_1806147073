package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity 
@Table(name = "maskapai")
public class MaskapaiModel implements Serializable {
    @Id
    @Size(max = 20)
	@Column(name = "id_maskapai")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaskapai;
    
    @NotNull
	@Size(max = 255)
	@Column(name = "nama_maskapai", nullable = false)
	private String namaMaskapai;

	@NotNull
	@Size(max = 255)
	@Column(name = "kode_maskapai", nullable = false)
    private String kodeMaskapai;
	

	public Long getIdMaskapai() {
		return this.idMaskapai;
	}

	public void setIdMaskapai(Long idMaskapai) {
		this.idMaskapai = idMaskapai;
	}

	public String getNamaMaskapai() {
		return this.namaMaskapai;
	}

	public void setNamaMaskapai(String namaMaskapai) {
		this.namaMaskapai = namaMaskapai;
	}

	public String getKodeMaskapai() {
		return this.kodeMaskapai;
	}

	public void setKodeMaskapai(String kodeMaskapai) {
		this.kodeMaskapai = kodeMaskapai;
	}

	public boolean isPresent() {
		return true;
	}
	
}