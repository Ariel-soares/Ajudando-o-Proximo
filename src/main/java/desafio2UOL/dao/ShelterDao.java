package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Shelter;
import jakarta.persistence.EntityManager;

public class ShelterDao {

	public Shelter findById(Integer id, EntityManager em) {
		Shelter shelter = em.find(Shelter.class, id);
		return shelter;
	}

	public List<Shelter> getAllShelters(EntityManager em) {
		List<Shelter> shelters = em.createQuery("SELECT s FROM Shelter s", Shelter.class).getResultList();
		return shelters;
	}

	public void deleteShelter(int id, EntityManager em) {
		em.getTransaction().begin();
		Shelter shelter = em.find(Shelter.class, id);
		if (shelter != null) {
			em.remove(shelter);
			System.out.println("ShelterRemoved!");
		}
		em.getTransaction().commit();
	}
	
	public void addShelter(Shelter shelter, EntityManager em) {
        em.getTransaction().begin();
        em.persist(shelter);
        em.getTransaction().commit();
    }

    public void updateShelter(Shelter shelter, Integer id, EntityManager em) {
        em.find(Shelter.class, shelter.getId());
        em.getTransaction().begin();
        em.merge(shelter);
        em.getTransaction().commit();
    }

}