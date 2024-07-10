package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.DistributionCenterDao;
import desafio2UOL.entities.DistributionCenter;
import jakarta.persistence.EntityManager;

public class DistributionCenterService {

	private DistributionCenterDao DistributionCenterDao = new DistributionCenterDao();
	

	public DistributionCenter findById(Integer id, EntityManager em) {
		return DistributionCenterDao.findById(id, em);
	}

	public List<DistributionCenter> getAllDistributionCenters(EntityManager em) {
		return DistributionCenterDao.getAllDistributionCenters(em);
	}

	public void addDistributionCenter(DistributionCenter DistributionCenter, EntityManager em) {
		DistributionCenterDao.addDistributionCenter(DistributionCenter, em);
	}

	public void updateDistributionCenter(DistributionCenter DistributionCenter, Integer id, EntityManager em) {
		DistributionCenter old = DistributionCenterDao.findById(id, em);
		updateData(old, DistributionCenter);
		DistributionCenterDao.updateDistributionCenter(old, id, em);
	}

	public void deleteDistributionCenter(int id, EntityManager em) {
		DistributionCenterDao.deleteDistributionCenter(id, em);
	}

	public void listDistributionCenters(EntityManager em) {
		List<DistributionCenter> DistributionCenters = DistributionCenterDao.getAllDistributionCenters(em);
		for (DistributionCenter DistributionCenter : DistributionCenters) {
			System.out.println(DistributionCenter);
		}
	}

	private void updateData(DistributionCenter old, DistributionCenter updated) {

		old.setAddress(updated.getAddress());
		old.setCep(updated.getCep());
		old.setCity(updated.getCity());;
		old.setId(updated.getId());
		old.setName(updated.getName());
		old.setState(updated.getState());
		old.setId(updated.getId());
		System.out.println("MAP FOR SAVING\n" + updated.getItems() + "----------------");
		System.out.println("\n not updated map\n" + old.getItems());
		System.out.println("cleared map\n" + old.getItems());
		old.getItems().putAll(updated.getItems());
		System.out.println("updated map\n" + old.getItems());
	}

	public void findOne(Integer id, EntityManager em) {
		DistributionCenter DistributionCenter = findById(id, em);
		if (DistributionCenter != null) {
			System.out.println(DistributionCenter);
			System.out.println("\n--------------- Lista de doações do abrigo -------------\n");
			System.out.println(DistributionCenter.getDonations());
		} else
			System.err.println("Entidade não encontrada");
	}
}