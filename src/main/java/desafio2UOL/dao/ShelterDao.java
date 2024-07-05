package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Shelter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ShelterDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");

	public List<Shelter> getAllShelters() {
		EntityManager em = emf.createEntityManager();
		List<Shelter> shelters = em.createQuery("SELECT s FROM Shelter s", Shelter.class).getResultList();
		em.close();
		return shelters;
	}

	public void deleteShelter(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Shelter shelter = em.find(Shelter.class, id);
		if (shelter != null) {
			em.remove(shelter);
			System.out.println("ShelterRemoved!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public void addShelter(Shelter shelter) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(shelter);
        em.getTransaction().commit();
        em.close();
    }

    public void updateShelter(Shelter shelter) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(shelter);
        em.getTransaction().commit();
        em.close();
    }

}