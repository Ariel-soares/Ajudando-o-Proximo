package desafio2UOL.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int quantity;
	private LocalDateTime time;

	@OneToOne
	private Shelter requester;

	@OneToOne
	private Item item;

	public Order() {
	}

	public Order(Shelter requester, Item item, int quantity) {
		this.id = null;
		this.requester = requester;
		this.item = item;
		this.time = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		this.setQuantity(quantity);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Shelter getRequester() {
		return requester;
	}

	public void setRequester(Shelter requester) {
		this.requester = requester;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
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
		Order other = (Order) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", quantity=" + quantity + ", time=" + time + ", requester=" + requester.getId() + ", item="
				+ item.getId() + "]";
	}
	
	
}
