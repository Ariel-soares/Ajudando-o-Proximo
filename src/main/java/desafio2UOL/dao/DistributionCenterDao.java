package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.DistributionCenter;
import jakarta.persistence.EntityManager;

public class DistributionCenterDao {

	public DistributionCenter findById(Integer id, EntityManager em) {
		DistributionCenter DistributionCenter = em.find(DistributionCenter.class, id);
		return DistributionCenter;
	}

	public List<DistributionCenter> getAllDistributionCenters(EntityManager em) {
		List<DistributionCenter> DistributionCenters = em.createQuery("SELECT s FROM DistributionCenter s", DistributionCenter.class).getResultList();
		return DistributionCenters;
	}

	public void deleteDistributionCenter(int id, EntityManager em) {
		em.getTransaction().begin();
		DistributionCenter DistributionCenter = em.find(DistributionCenter.class, id);
		if (DistributionCenter != null) {
			em.remove(DistributionCenter);
			System.out.println("DistributionCenter Removed!");
		}
		em.getTransaction().commit();
	}

	public void addDistributionCenter(DistributionCenter DistributionCenter, EntityManager em) {
		em.getTransaction().begin();
		em.persist(DistributionCenter);
		em.getTransaction().commit();
	}

	public void updateDistributionCenter(DistributionCenter DistributionCenter, Integer id, EntityManager em) {
		em.find(DistributionCenter.class, DistributionCenter.getId());
		em.getTransaction().begin();
		em.merge(DistributionCenter);
		em.getTransaction().commit();
	}

}