package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Item;
import jakarta.persistence.EntityManager;

public class ItemDao {

	
	public Item findById(Integer id, EntityManager em) {
		Item Item = em.find(Item.class, id);
		return Item;
	}

	public List<Item> getAllItems(EntityManager em) {
		List<Item> Items = em.createQuery("SELECT s FROM Item s", Item.class).getResultList();
		return Items;
	}

	public void deleteItem(int id, EntityManager em) {
		em.getTransaction().begin();
		Item Item = em.find(Item.class, id);
		if (Item != null) {
			em.remove(Item);
			System.out.println("ItemRemoved!");
		}
		em.getTransaction().commit();
	}
	
	public void addItem(Item Item, EntityManager em) {
        em.getTransaction().begin();
        em.persist(Item);
        em.getTransaction().commit();
    }

    public void updateItem(Item Item, Integer id, EntityManager em) {
        em.find(Item.class, Item.getId());
        em.getTransaction().begin();
        em.merge(Item);
        em.getTransaction().commit();
    }

}