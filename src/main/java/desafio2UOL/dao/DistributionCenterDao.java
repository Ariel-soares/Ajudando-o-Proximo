package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.DistributionCenter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DistributionCenterDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");

	public DistributionCenter findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		DistributionCenter DistributionCenter = em.find(DistributionCenter.class, id);
		em.close();
		return DistributionCenter;
	}

	public List<DistributionCenter> getAllDistributionCenters() {
		EntityManager em = emf.createEntityManager();
		List<DistributionCenter> DistributionCenters = em.createQuery("SELECT s FROM DistributionCenter s", DistributionCenter.class).getResultList();
		em.close();
		return DistributionCenters;
	}

	public void deleteDistributionCenter(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		DistributionCenter DistributionCenter = em.find(DistributionCenter.class, id);
		if (DistributionCenter != null) {
			em.remove(DistributionCenter);
			System.out.println("DistributionCenter Removed!");
		}
		em.getTransaction().commit();
		em.close();
	}

	public void addDistributionCenter(DistributionCenter DistributionCenter) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(DistributionCenter);
		em.getTransaction().commit();
		em.close();
	}

	public void updateDistributionCenter(DistributionCenter DistributionCenter, Integer id) {
		EntityManager em = emf.createEntityManager();
		em.find(DistributionCenter.class, DistributionCenter.getId());
		em.getTransaction().begin();
		em.merge(DistributionCenter);
		em.getTransaction().commit();
		em.close();
	}

}