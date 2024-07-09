package desafio2UOL.entities;

import java.util.Objects;

import desafio2UOL.entities.enums.ItemName;
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
	private ItemName name;
	private String description;

	public HygieneItem() {
	}

	public HygieneItem(ItemName name, String description) {
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItemName getNome() {
		return name;
	}

	public void setNome(ItemName nome) {
		this.name = nome;
	}

	public String getDescricao() {
		return description;
	}

	public void setDescricao(String descricao) {
		this.description = descricao;
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
		return "Hygiene type item of name: " + name + ", with description: " + description + "\n";
	}

}
