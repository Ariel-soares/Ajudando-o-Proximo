package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ItemDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");
	
	public Item findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Item Item = em.find(Item.class, id);
		em.close();
		return Item;
	}

	public List<Item> getAllItems() {
		EntityManager em = emf.createEntityManager();
		List<Item> Items = em.createQuery("SELECT s FROM Item s", Item.class).getResultList();
		em.close();
		return Items;
	}

	public void deleteItem(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Item Item = em.find(Item.class, id);
		if (Item != null) {
			em.remove(Item);
			System.out.println("ItemRemoved!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public void addItem(Item Item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(Item);
        em.getTransaction().commit();
        em.close();
    }

    public void updateItem(Item Item, Integer id) {
        EntityManager em = emf.createEntityManager();
        em.find(Item.class, Item.getId());
        em.getTransaction().begin();
        em.merge(Item);
        em.getTransaction().commit();
        em.close();
    }

}