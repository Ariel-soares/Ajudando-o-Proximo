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
	private int id;
	private int quantity;
	private LocalDateTime time;

	@OneToOne
	private Shelter requesterId;

	@OneToOne
	private Item item;

	public Order() {
	}

	public Order(int id, Shelter requesterId, Item item, int quantity) {
		this.id = id;
		this.requesterId = requesterId;
		this.item = item;
		this.time = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		this.setQuantity(quantity);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Shelter getRequesterId() {
		return requesterId;
	}

	public void setRequester(Shelter requesterId) {
		this.requesterId = requesterId;
	}

	public Item getItem() {
		return item;
	}

	public void setItens(Item item) {
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
}
