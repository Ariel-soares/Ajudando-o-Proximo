package desafio2UOL;

import java.util.Scanner;

import desafio2UOL.entities.Shelter;
import desafio2UOL.services.ShelterService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static ShelterService shelterService = new ShelterService();

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");
		EntityManager em = emf.createEntityManager();

		Shelter shelter = new Shelter(null, "abrigo 1", "rua 2", "s", "e", "d", 4, 5);

		em.getTransaction().begin();
		em.persist(shelter);
		em.getTransaction().commit();

		shelterService.listShelters();
		
		shelter.setName("abrigo 1 atualizado");
		
		shelterService.updateShelter(shelter, 1);
		
		shelterService.listShelters();
		
		/*
		 * DistributionCenter cd = new DistributionCenter(null, "cd1", "rua 1",
		 * "campinas", "RS", "25642");
		 * 
		 * em.getTransaction().begin(); em.persist(cd); em.getTransaction().commit();
		 * 
		 * Item cloth = new ClothItem(null, "Camisa", "Camisa Masculina branca", 'M',
		 * "M"); Item cloth2 = new ClothItem(null, "Moletom", "Moletom feminino preto",
		 * 'F', "M");
		 * 
		 * em.getTransaction().begin(); em.persist(cloth); em.persist(cloth2);
		 * em.getTransaction().commit();
		 * 
		 * Donation donation = new Donation(null, cd);
		 * 
		 * donation.addItem(cloth); donation.addItem(cloth2);
		 * 
		 * em.getTransaction().begin(); em.persist(donation);
		 * em.getTransaction().commit();
		 * 
		 * System.out.println("Operação concluída");
		 * 
		 * Donation result = em.find(Donation.class, 1);
		 * 
		 * for (Item item : result.getItens()) { System.out.println(item); }
		 */
		emf.close();
		em.close();

	}

	private static void showMenu() {
		while (true) {
			System.out.println("1. Add Donation");
			System.out.println("2. List Donations");
			System.out.println("3. Shelter Management");
			System.out.println("4. Distribution Center Management");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				// addDonation();
				break;
			case 2:
				// listDonations();
				break;
			case 3:
				// addShelter();
				break;
			case 4:
				shelterService.listShelters();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
			}
		}
	}

	private static void showShelterMenu() {
		while (true) {
			System.out.println("1. List Shelters");
			System.out.println("2. Find Specific Shelter");
			System.out.println("3. Add Shelter");
			System.out.println("4. Delete Shelter");
			System.out.println("5. Update Shelter");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				shelterService.listShelters();
				break;
			case 2:
				// listDonations();
				break;
			case 3:
				// addShelter();
				break;
			case 4:
				shelterService.listShelters();
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			default:
				System.out.println("Invalid option");
			}
		}
	}

}