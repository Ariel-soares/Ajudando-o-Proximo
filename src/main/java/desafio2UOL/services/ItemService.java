package desafio2UOL.services;

import java.util.List;

import desafio2UOL.dao.ItemDao;
import desafio2UOL.entities.Item;
import jakarta.persistence.EntityManager;

public class ItemService {
	
	private ItemDao itemDao = new ItemDao();
	
	public Item findById(Integer id, EntityManager em) {
		return itemDao.findById(id, em);
	}
	
	public List<Item> getAllItems(EntityManager em) {
        return itemDao.getAllItems(em);
    }
	
	public void addItem(Item Item, EntityManager em) {
        itemDao.addItem(Item, em);
    }
	
	public void addItemList(List<Item> items, EntityManager em) {
        for(Item i : items) {
        	itemDao.addItem(i, em);
        };
    }
/*
    public void updateItem(Item Item, Integer id) {
    	Item old = ItemDao.findById(id);
    	updateData(old, Item);
        ItemDao.updateItem(old, id);
    }

    public void deleteItem(int id) {
        ItemDao.deleteItem(id);
    }

    
    public void listItems() {
		List<Item> Items = ItemDao.getAllItems();
		for (Item Item : Items) {
			System.out.println(Item);
		}
	}
    
    private void updateData(Item old, Item updated) {
    	old.setAddress(updated.getAddress());
    	old.setCapacity(updated.getCapacity());
    	old.setEmail(updated.getEmail());
    	old.setId(updated.getId());
    	old.setName(updated.getName());
    	old.setOccupancy(updated.getOccupancy());
    	old.setPhoneNumber(updated.getPhoneNumber());
    	old.setResponsible(updated.getResponsible());
    }
    
    public void findOne(Integer id) {
    	Item Item = findById(id);
    	if(Item != null) {
    		System.out.println(Item);
    		System.out.println("\n--------------- Lista de itens do abrigo -------------\n");
    		System.out.println(Item.getItens());
    	} else
    		System.err.println("Entidade não encontrada");
    }*/
}