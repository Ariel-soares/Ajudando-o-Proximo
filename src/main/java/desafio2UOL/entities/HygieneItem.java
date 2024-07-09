package desafio2UOL.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hygiene_items")
public class HygieneItem extends Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Integer quantity;

	public HygieneItem() {
	}

	public HygieneItem(Integer id, String name, String description, Integer quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getDescricao() {
		return description;
	}

	public void setDescricao(String descricao) {
		this.description = descricao;
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
		HygieneItem other = (HygieneItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Item de higiene do tipo " + name + ", com a descrição: " + description + "\n";
	}

}
