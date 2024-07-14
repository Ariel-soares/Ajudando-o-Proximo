package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.OrderDao;
import desafio2UOL.entities.Order;
import jakarta.persistence.EntityManager;

public class OrderService {

	private OrderDao OrderDao = new OrderDao();

	public Order findById(Integer id, EntityManager em) {
		return OrderDao.findById(id, em);
	}

	public List<Order> getAllOrders(EntityManager em) {
		return OrderDao.getAllOrders(em);
	}

	public void addOrder(Order Order, EntityManager em) {
		OrderDao.addOrder(Order, em);
	}

	public void updateOrder(Order Order, Integer id, EntityManager em) {
		Order old = OrderDao.findById(id, em);
		OrderDao.updateOrder(old, id, em);
	}

	public void deleteOrder(int id, EntityManager em) {
		OrderDao.deleteOrder(id, em);
	}

	public void listOrders(EntityManager em) {
		List<Order> Orders = OrderDao.getAllOrders(em);
		for (Order Order : Orders) {
			System.out.println(Order);
		}
	}

	public void findOne(Integer id, EntityManager em) {
		Order order = findById(id, em);
		if (order != null) {
			System.out.println(order);
			System.out.println("\n--------------- Requested item code -------------\n");
			System.out.println(order.getItemCode());
		} else
			System.err.println("Entity not found");
	}
}