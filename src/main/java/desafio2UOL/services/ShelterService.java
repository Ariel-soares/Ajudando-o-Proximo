package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.ShelterDao;
import desafio2UOL.entities.Shelter;

public class ShelterService {
	
	private ShelterDao shelterDao = new ShelterDao();
	
	public Shelter findById(Integer id) {
		return shelterDao.findById(id);
	}
	
	public List<Shelter> getAllShelters() {
        return shelterDao.getAllShelters();
    }
	
	public void addShelter(Shelter shelter) {
        shelterDao.addShelter(shelter);
    }

    public void updateShelter(Shelter shelter, Integer id) {
    	Shelter old = shelterDao.findById(id);
    	updateData(old, shelter);
        shelterDao.updateShelter(old, id);
    }

    public void deleteShelter(int id) {
        shelterDao.deleteShelter(id);
    }

    
    public void listShelters() {
		List<Shelter> shelters = shelterDao.getAllShelters();
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
    
    private void findOne(Integer id) {
    	Shelter shelter = findById(id);
    	if(shelter != null) {
    		System.out.println(shelter);
    		System.out.println("--------------- Lista de itens do abrigo -------------");
    		System.out.println(shelter.getItens());
    	} else
    		System.err.println("Entidade não encontrada");
    }
}