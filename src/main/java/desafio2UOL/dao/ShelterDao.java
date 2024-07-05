package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Shelter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ShelterDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");
	
	public List<Shelter> getAllShelters(){
		EntityManager em = emf.createEntityManager();
		List<Shelter> shelters = em.createQuery("SELECT s FROM Shelter s", Shelter.class).getResultList();
		em.close();
		return shelters;
	}
	
}