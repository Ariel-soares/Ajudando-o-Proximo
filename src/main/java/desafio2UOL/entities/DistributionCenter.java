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
	private Integer clothItems;
	private Integer foodItems;
	private Integer hygieneItems;

	@OneToMany(mappedBy = "distributionCenter", fetch = FetchType.EAGER)
	private List<Donation> donations = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER)
	private List<Order> orders = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "distributioncenter_items", joinColumns = {
			@JoinColumn(name = "distributioncenter_id", referencedColumnName = "id") })
	@Column(name = "quantity")
	@MapKeyJoinColumn(name = "item")
	private Map<String, Integer> items = new LinkedHashMap<>();

	public DistributionCenter() {
	}

	public DistributionCenter(Integer id, String name, String address, String city, String state, String cep) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.cep = cep;
		this.clothItems = 0;
		this.foodItems = 0;
		this.hygieneItems = 0;
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

	public Map<String, Integer> getItems() {
		return items;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public Integer getClothItems() {
		return clothItems;
	}

	public void setClothItems(Integer clothItems) {
		this.clothItems = clothItems;
	}

	public Integer getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(Integer foodItems) {
		this.foodItems = foodItems;
	}

	public Integer getHygieneItems() {
		return hygieneItems;
	}

	public void setHygieneItems(Integer hygieneItems) {
		this.hygieneItems = hygieneItems;
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
		return "DistributionCenter " + name + ", located at " + address + ", in the city " + city + " in the state of "
				+ state + " with CEP " + cep + "\n Number of cloth items available: " + clothItems
				+ "\n Number of food items available: " + foodItems + "\n Number of hygiene items available: " + hygieneItems;
	}

}
