package desafio2UOL.dao;

import java.util.List;

import desafio2UOL.entities.Donation;
import jakarta.persistence.EntityManager;

public class DonationDao {

	
	public Donation findById(Integer id, EntityManager em) {
		Donation Donation = em.find(Donation.class, id);
		return Donation;
	}

	public List<Donation> getAllDonations(EntityManager em) {
		List<Donation> Donations = em.createQuery("SELECT s FROM Donation s", Donation.class).getResultList();
		return Donations;
	}

	public void deleteDonation(int id, EntityManager em) {
		em.getTransaction().begin();
		Donation Donation = em.find(Donation.class, id);
		if (Donation != null) {
			em.remove(Donation);
			System.out.println("Donation Removed!");
		}
		em.getTransaction().commit();
	}
	
	public void addDonation(Donation Donation, EntityManager em) {
        em.getTransaction().begin();
        em.persist(Donation);
        em.getTransaction().commit();
    }

    public void updateDonation(Donation Donation, Integer id, EntityManager em) {
        em.find(Donation.class, Donation.getId());
        em.getTransaction().begin();
        em.merge(Donation);
        em.getTransaction().commit();
    }

}