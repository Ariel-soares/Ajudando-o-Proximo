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

}