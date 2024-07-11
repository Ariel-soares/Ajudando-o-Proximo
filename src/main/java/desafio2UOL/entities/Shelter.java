package desafio2UOL.entities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "shelters")
public class Shelter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String responsible;
	@Column(name = "phoneNumber")
	private String phoneNumber;
	private String email;
	private Integer capacity;
	private Integer occupancy;

	@ElementCollection
	@CollectionTable(name = "shelter_items", joinColumns = {
			@JoinColumn(name = "shelter_id", referencedColumnName = "id") })
	@Column(name = "quantity")
	@MapKeyJoinColumn(name = "item")
	private Map<String, Integer> items = new LinkedHashMap<>();
	
	public Shelter() {}

	public Shelter(Integer id, String name, String address, String responsible, String phoneNumber, String email,
			Integer capacity, Integer occupancy) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.responsible = responsible;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.capacity = capacity;
		this.occupancy = occupancy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}
	

	public Map<String, Integer> getItems() {
		return items;
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
		Shelter other = (Shelter) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Shelter [id=" + id + ", name=" + name + ", address=" + address + ", responsible=" + responsible
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", capacity=" + capacity + ", occupancy="
				+ occupancy + "]";
	}

}
