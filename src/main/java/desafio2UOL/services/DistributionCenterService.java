package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.DistributionCenterDao;
import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Donation;
import desafio2UOL.entities.Item;

public class DistributionCenterService {

	private DistributionCenterDao DistributionCenterDao = new DistributionCenterDao();

	public DistributionCenter findById(Integer id) {
		return DistributionCenterDao.findById(id);
	}

	public List<DistributionCenter> getAllDistributionCenters() {
		return DistributionCenterDao.getAllDistributionCenters();
	}

	public void addDistributionCenter(DistributionCenter DistributionCenter) {
		DistributionCenterDao.addDistributionCenter(DistributionCenter);
	}

	public void updateDistributionCenter(DistributionCenter DistributionCenter, Integer id) {
		DistributionCenter old = DistributionCenterDao.findById(id);
		updateData(old, DistributionCenter);
		DistributionCenterDao.updateDistributionCenter(old, id);
	}

	public void deleteDistributionCenter(int id) {
		DistributionCenterDao.deleteDistributionCenter(id);
	}

	public void listDistributionCenters() {
		List<DistributionCenter> DistributionCenters = DistributionCenterDao.getAllDistributionCenters();
		for (DistributionCenter DistributionCenter : DistributionCenters) {
			System.out.println(DistributionCenter);
		}
	}
	
	public void addDonation(Donation donation, Integer id) {
		DistributionCenter center = DistributionCenterDao.findById(id);
		center.getDonations().add(donation);
		for(Item i : donation.getItens()) {
			center.getItems().add(i);
		}
		DistributionCenterDao.updateDistributionCenter(center, id);
	}

	private void updateData(DistributionCenter old, DistributionCenter updated) {

		old.setAddress(updated.getAddress());
		old.setCep(updated.getCep());
		old.setCity(updated.getCity());;
		old.setId(updated.getId());
		old.setName(updated.getName());
		old.setState(updated.getState());
		old.setId(updated.getId());

	}

	public void findOne(Integer id) {
		DistributionCenter DistributionCenter = findById(id);
		if (DistributionCenter != null) {
			System.out.println(DistributionCenter);
			System.out.println("\n--------------- Lista de doações do abrigo -------------\n");
			System.out.println(DistributionCenter.getDonations());
		} else
			System.err.println("Entidade não encontrada");
	}
}