package desafio2UOL.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "donations")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime time;
	private Integer quantity;
	private String observation;

	@OneToOne
	private Item item;

	@ManyToOne
	@JoinColumn(name = "distributioncenter_id")
	private DistributionCenter distributionCenter;

	public Donation() {
	}

	public Donation(DistributionCenter distributionCenter, Integer quantity, Item item) {
		this.time = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		this.distributionCenter = distributionCenter;
		this.quantity = quantity;
		this.item = item;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DistributionCenter getDistributionCenter() {
		return distributionCenter;
	}

	public void setCenterId(DistributionCenter distributionCenter) {
		this.distributionCenter = distributionCenter;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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
		return "\nDonation number " + id + ", made to distribution center " + distributionCenter.getName() + " adding "
				+ quantity + " " + item.getName() + "(s) to its storage\n";
	}

}
