package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.ShelterDao;
import desafio2UOL.entities.Shelter;

public class ShelterService {
	
	private ShelterDao shelterDao = new ShelterDao();
	
	public List<Shelter> getAllShelters() {
        return shelterDao.getAllShelters();
    }

}
