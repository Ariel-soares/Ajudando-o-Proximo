package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.ShelterDao;
import desafio2UOL.entities.Item;
import desafio2UOL.entities.Shelter;
import jakarta.persistence.EntityManager;

public class ShelterService {
	
	private ShelterDao shelterDao = new ShelterDao();
	
	public Shelter findById(Integer id, EntityManager em) {
		return shelterDao.findById(id, em);
	}
	
	public List<Shelter> getAllShelters(EntityManager em) {
        return shelterDao.getAllShelters(em);
    }
	
	public void addShelter(Shelter shelter, EntityManager em) {
        shelterDao.addShelter(shelter, em);
    }

    public void updateShelter(Shelter shelter, Integer id, EntityManager em) {
    	Shelter old = shelterDao.findById(id, em);
    	updateData(old, shelter);
        shelterDao.updateShelter(old, id, em);
    }

    public void deleteShelter(int id, EntityManager em) {
        shelterDao.deleteShelter(id, em);
    }

    
    public void listShelters(EntityManager em) {
		List<Shelter> shelters = shelterDao.getAllShelters(em);
		for (Shelter shelter : shelters) {
			System.out.println(shelter);
		}
	}
    
    private void updateData(Shelter old, Shelter updated) {
    	old.setAddress(updated.getAddress());
    	old.setCapacity(updated.getCapacity());
    	old.setEmail(updated.getEmail());
    	old.setId(updated.getId());
    	old.setName(updated.getName());
    	old.setOccupancy(updated.getOccupancy());
    	old.setPhoneNumber(updated.getPhoneNumber());
    	old.setResponsible(updated.getResponsible());
    }
    
    public void findOne(Integer id, EntityManager em) {
    	Shelter shelter = findById(id, em);
    	if(shelter != null) {
    		System.out.println(shelter);
    		System.out.println("\n--------------- Lista de itens do abrigo -------------\n");
    		for(Item i : shelter.getItens()) {
    			System.out.println(i);
    		}
    	} else
    		System.err.println("Entidade nao encontrada");
    }
}