package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.DonationDao;
import desafio2UOL.entities.Donation;

public class DonationService {
	
	private DonationDao DonationDao = new DonationDao();
	
	public Donation findById(Integer id) {
		return DonationDao.findById(id);
	}
	
	public List<Donation> getAllDonations() {
        return DonationDao.getAllDonations();
    }
	
	public void addDonation(Donation Donation) {
        DonationDao.addDonation(Donation);
    }
/*
    public void updateDonation(Donation Donation, Integer id) {
    	Donation old = DonationDao.findById(id);
    	updateData(old, Donation);
        DonationDao.updateDonation(old, id);
    }

    public void deleteDonation(int id) {
        DonationDao.deleteDonation(id);
    }

    
    public void listDonations() {
		List<Donation> Donations = DonationDao.getAllDonations();
		for (Donation Donation : Donations) {
			System.out.println(Donation);
		}
	}
    
    private void updateData(Donation old, Donation updated) {
    	old.setAddress(updated.getAddress());
    	old.setCapacity(updated.getCapacity());
    	old.setEmail(updated.getEmail());
    	old.setId(updated.getId());
    	old.setName(updated.getName());
    	old.setOccupancy(updated.getOccupancy());
    	old.setPhoneNumber(updated.getPhoneNumber());
    	old.setResponsible(updated.getResponsible());
    }*/
    
    public void findOne(Integer id) {
    	Donation Donation = findById(id);
    	if(Donation != null) {
    		System.out.println(Donation);
    		System.out.println("\n--------------- Lista de itens do abrigo -------------\n");
    		System.out.println(Donation.getItens());
    	} else
    		System.err.println("Entidade não encontrada");
    }
}