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
@Table(name = "cloth_items")
public class ClothItem extends Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private ItemName name;
	private String description;
	private char gender;
	private String size;

	public ClothItem() {
	}

	public ClothItem(ItemName name, String description, char gender, String size) {
		this.name = name;
		this.description = description;
		this.gender = gender;
		this.size = size;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public ItemName getName() {
		return name;
	}

	public void setName(ItemName name) {
		this.name = name;
	}

	@Override
	public String storageCode() {
		return super.getItemType() + "/" + name.toString() + "/" + gender + "/" + size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
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
		ClothItem other = (ClothItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return " Cloth item type with description: " + description + ", of name : " + name + ", of gender " + gender
				+ ", of size " + size;
	}

}
