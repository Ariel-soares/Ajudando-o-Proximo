package desafio2UOL.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "donations")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime time;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Item> itens = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "distributioncenter_id")
	private DistributionCenter centerId;
	
	public Donation() {}

	public Donation(Integer id, DistributionCenter centerId) {
		this.id = id;
		this.time =LocalDateTime.now();
		this.centerId = centerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Item> getItens() {
		return itens;
	}


	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DistributionCenter getCenterId() {
		return centerId;
	}

	public void setCenterId(DistributionCenter centerId) {
		this.centerId = centerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donation other = (Donation) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", itens=" + itens  + ", time=" + time + ", centerId=" + centerId + "]";
	}
	
	public void addItem(Item item) {
		this.itens.add(item);
	}
}
