package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.DonationDao;
import desafio2UOL.entities.Donation;
import jakarta.persistence.EntityManager;

public class DonationService {

	private DonationDao DonationDao = new DonationDao();

	public Donation findById(Integer id, EntityManager em) {
		return DonationDao.findById(id, em);
	}

	public List<Donation> getAllDonations(EntityManager em) {
		return DonationDao.getAllDonations(em);
	}

	public void addDonation(Donation Donation, EntityManager em) {
		DonationDao.addDonation(Donation, em);
	}

	public void findOne(Integer id, EntityManager em) {
		Donation Donation = findById(id, em);
		if (Donation != null) {
			System.out.println(Donation);
			System.out.println("\n--------------- Donation Item -------------\n");
			System.out.println(Donation.getItem());
		} else
			System.err.println("Entity not found");
	}
}