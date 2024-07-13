package desafio2UOL.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int quantity;
	private LocalDateTime time;
	private String itemCode;
	private String attendance;
	private Boolean attended;

	@ManyToOne
	private Shelter requester;

	public Order() {
		this.time = LocalDateTime.now();
	}

	public Order(Shelter requester, String itemCode, int quantity) {
		this.id = null;
		this.requester = requester;
		this.itemCode = itemCode;
		this.time = LocalDateTime.now();
		this.setQuantity(quantity);
		this.attended = false;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItem(String itemCode) {
		this.itemCode = itemCode;
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

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public Boolean getAttended() {
		return attended;
	}

	public void setAttended(Boolean attended) {
		this.attended = attended;
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
		return "Order [id=" + id + ", quantity=" + quantity + ", time=" + time + ", requester=" + requester.getId() + ", itemCode="
				+ itemCode + "]";
	}
	
	
}
