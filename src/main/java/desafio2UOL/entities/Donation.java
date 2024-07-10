package desafio2UOL.entities;

import java.time.LocalDateTime;
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
/*
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "donation_items", joinColumns = @JoinColumn(name = "Donation_id"))
	private List<Item> itens = new ArrayList<>();*/
	
	@OneToOne
	private Item item;

	@ManyToOne
	@JoinColumn(name = "distributioncenter_id")
	private DistributionCenter centerId;

	public Donation() {
	}

	public Donation(DistributionCenter centerId, Integer quantity, Item item) {
		this.time = LocalDateTime.now();
		this.centerId = centerId;
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

	public DistributionCenter getCenterId() {
		return centerId;
	}

	public void setCenterId(DistributionCenter centerId) {
		this.centerId = centerId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
		return "\nDonation number " + id + ", made to distribution center " + centerId.getName() + " adding "
				+ quantity + item.getName() + "(s) to its storage\n";
	}

}
