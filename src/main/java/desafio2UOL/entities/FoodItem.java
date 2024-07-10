package desafio2UOL.entities;

import java.util.Objects;

import desafio2UOL.entities.enums.ItemName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItem extends Item{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private ItemName name;
	private String description;
	private String measurement;
	private String validity;
	
	public FoodItem() {}

	public FoodItem(ItemName name, String description, String measurement, String validity) {
		this.name = name;
		this.description = description;
		this.measurement = measurement;
		this.validity = validity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ItemName getName() {
		return name;
	}
	
	public void setName(ItemName name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	@Override
	public String storageCode() {
		return super.getItemType().toString() + "/" + name.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodItem other = (FoodItem) obj;
		return name == other.name;
	}

	@Override
	public String toString() {
		return "Food type item, with description: " + description + ", spoiling at date: " + validity + ", measured in:" + measurement + super.getItemType() +"\n";
	}
	
}
