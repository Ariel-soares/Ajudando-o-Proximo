package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Order;
import jakarta.persistence.EntityManager;

public class OrderDao {

	public Order findById(Integer id, EntityManager em) {
		Order Order = em.find(Order.class, id);
		return Order;
	}

	public List<Order> getAllOrders(EntityManager em) {
		List<Order> Orders = em.createQuery("SELECT s FROM Order s", Order.class).getResultList();
		return Orders;
	}

	public void deleteOrder(int id, EntityManager em) {
		em.getTransaction().begin();
		Order Order = em.find(Order.class, id);
		if (Order != null) {
			em.remove(Order);
			System.out.println("OrderRemoved!");
		}
		em.getTransaction().commit();
	}
	
	public void addOrder(Order Order, EntityManager em) {
        em.getTransaction().begin();
        em.persist(Order);
        em.getTransaction().commit();
    }

    public void updateOrder(Order Order, Integer id, EntityManager em) {
        em.find(Order.class, Order.getId());
        em.getTransaction().begin();
        em.merge(Order);
        em.getTransaction().commit();
    }

}