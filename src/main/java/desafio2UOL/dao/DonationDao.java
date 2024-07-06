package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Donation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DonationDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");
	
	public Donation findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Donation Donation = em.find(Donation.class, id);
		em.close();
		return Donation;
	}

	public List<Donation> getAllDonations() {
		EntityManager em = emf.createEntityManager();
		List<Donation> Donations = em.createQuery("SELECT s FROM Donation s", Donation.class).getResultList();
		em.close();
		return Donations;
	}

	public void deleteDonation(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Donation Donation = em.find(Donation.class, id);
		if (Donation != null) {
			em.remove(Donation);
			System.out.println("Donation Removed!");
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public void addDonation(Donation Donation) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(Donation);
        em.getTransaction().commit();
        em.close();
    }

    public void updateDonation(Donation Donation, Integer id) {
        EntityManager em = emf.createEntityManager();
        em.find(Donation.class, Donation.getId());
        em.getTransaction().begin();
        em.merge(Donation);
        em.getTransaction().commit();
        em.close();
    }

}