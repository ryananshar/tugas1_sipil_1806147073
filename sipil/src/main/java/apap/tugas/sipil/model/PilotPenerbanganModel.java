package apap.tugas.sipil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity 
@Table(name = "pilot_penerbangan")
public class PilotPenerbanganModel implements Serializable {
    @Id
    @Size(max = 20)
	@Column(name = "id_pilot_penerbangan")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilotPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("idPilot")
    @JoinColumn(name = "no_pilot", referencedColumnName = "id_pilot", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PilotModel pilotModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("idPenerbangan")
    @JoinColumn(name = "no_penerbangan", referencedColumnName = "id_penerbangan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PenerbanganModel penerbanganModel;

    @NotNull
	@Column(name = "tanggal_penugasan", nullable = false)
	private Date tanggalPenugasan;


    public Long getIdPilotPenerbangan() {
        return this.idPilotPenerbangan;
    }

    public void setIdPilotPenerbangan(Long idPilotPenerbangan) {
        this.idPilotPenerbangan = idPilotPenerbangan;
    }

    public PilotModel getPilotModel() {
        return this.pilotModel;
    }

    public void setPilotModel(PilotModel pilotModel) {
        this.pilotModel = pilotModel;
    }

    public PenerbanganModel getPenerbanganModel() {
        return this.penerbanganModel;
    }

    public void setPenerbanganModel(PenerbanganModel penerbanganModel) {
        this.penerbanganModel = penerbanganModel;
    }

    public Date getTanggalPenugasan() {
        return this.tanggalPenugasan;
    }

    public void setTanggalPenugasan(Date tanggalPenugasan) {
        this.tanggalPenugasan = tanggalPenugasan;
    }
    
    public boolean isPresent() {
		return true;
	}
}