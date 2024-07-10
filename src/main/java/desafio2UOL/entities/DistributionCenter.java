package desafio2UOL.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "distribution_centers")
public class DistributionCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String cep;

	@OneToMany(mappedBy = "centerId", fetch = FetchType.EAGER)
	private List<Donation> donations = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER)
	private List<Order> orders = new ArrayList<>();

	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ElementCollection
	// @JoinTable(name = "distribution_center_items",
	// joinColumns = {@JoinColumn(name = "distributionCenter_id",
	// referencedColumnName = "id")},
	// inverseJoinColumns = @JoinColumn(name = "items_quantity"))
	// @MapKeyJoinColumn(name = "item_id")
	@CollectionTable(name = "distributioncenter_items", joinColumns = {
			@JoinColumn(name = "distributioncenter_id", referencedColumnName = "id") })
	@Column(name = "quantity")
	@MapKeyJoinColumn(name = "item")
	private Map<Item, Integer> items = new LinkedHashMap<>();

	public DistributionCenter() {
	}

	public DistributionCenter(Integer id, String name, String address, String city, String state, String cep) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.cep = cep;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public Map<Item, Integer> getItems() {
		return items;
	}

	public List<Donation> getDonations() {
		return donations;
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
		DistributionCenter other = (DistributionCenter) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DistributionCenter [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", cep=" + cep + "]";
	}

}
