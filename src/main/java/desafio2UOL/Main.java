package desafio2UOL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import desafio2UOL.entities.ClothItem;
import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Donation;
import desafio2UOL.entities.Item;
import desafio2UOL.entities.Shelter;
import desafio2UOL.services.DistributionCenterService;
import desafio2UOL.services.ShelterService;
import desafio2UOL.views.DonationsMenus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static ShelterService shelterService = new ShelterService();
	private static DistributionCenterService distributionCenterService = new DistributionCenterService();

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");
		EntityManager em = emf.createEntityManager();

		Shelter shelter = new Shelter(null, "abrigo 1", "rua 2", "s", "e", "d", 4, 5);

		em.getTransaction().begin();
		em.persist(shelter);
		em.getTransaction().commit();

		Item cloth = new ClothItem(null, "Camisa", "Camisa Masculina branca", 'M', "M");
		Item cloth2 = new ClothItem(null, "Moletom", "Moletom feminino preto", 'F', "M");

		shelter.getItens().add(cloth2);
		shelter.getItens().add(cloth);

		//System.out.println(shelter.getItens());

		em.getTransaction().begin();
		em.persist(cloth);
		em.persist(cloth2);
		em.persist(shelter);
		em.getTransaction().commit();

		DistributionCenter cd = new DistributionCenter(null, "cd1", "rua 1", "campinas", "RS", "25642");

		em.getTransaction().begin();
		em.persist(cd);
		em.getTransaction().commit();

		Donation donation = new Donation(null, cd);

		donation.addItem(cloth);
		donation.addItem(cloth2);

		em.getTransaction().begin();
		em.persist(donation);
		em.getTransaction().commit();

		cd.getDonations().add(donation);

		em.getTransaction().begin();
		em.persist(cd);
		em.getTransaction().commit();

		//System.out.println(cd.getDonations());

		showMenu();
		/*
		 * DistributionCenter cd = new DistributionCenter(null, "cd1", "rua 1",
		 * "campinas", "RS", "25642");
		 * 
		 * em.getTransaction().begin(); em.persist(cd); em.getTransaction().commit();
		 * 
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
			System.out.println("1. Donation Management");
			System.out.println("2. List Donations");
			System.out.println("3. Shelter Management");
			System.out.println("4. Distribution Center Management");
			System.out.println("5. Exit");
			System.out.print("\nChoose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				showDonationsMenu();
				break;
			case 2:
				// listDonations();
				break;
			case 3:
				showShelterMenu();
				break;
			case 4:

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
			System.out.println("\n1. List Shelters");
			System.out.println("2. Find Specific Shelter");
			System.out.println("3. Add Shelter");
			System.out.println("4. Delete Shelter");
			System.out.println("5. Update Shelter");
			System.out.println("6. Exit");
			System.out.print("\nChoose an option: \n");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				shelterService.listShelters();
				break;
			case 2:
				System.out.println("\n\nEnter Shelter Id: \n");
				Integer idForFound = scanner.nextInt();
				shelterService.findOne(idForFound);
				break;
			case 3:
				addShelter();
				break;
			case 4:
				System.out.println("Enter Shelter Id: ");
				Integer id = scanner.nextInt();
				shelterService.deleteShelter(id);
				break;
			case 5:
				showUpdateShelterMenu();
				break;
			case 6:
				System.out.println("\nReturning to main menu \n");
				return;
			default:
				System.out.println("\nInvalid option  \n");
			}
		}
	}

	private static void addShelter() {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter address: ");
		String address = scanner.nextLine();
		System.out.print("Enter contact: ");
		String contact = scanner.nextLine();
		System.out.print("Enter capacity: ");
		int capacity = scanner.nextInt();
		System.out.print("Enter occupancy: ");
		int occupancy = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter responsible: ");
		String responsible = scanner.nextLine();
		System.out.print("Enter email: ");
		String email = scanner.nextLine();

		Shelter shelter = new Shelter(null, name, address, responsible, contact, email, capacity, occupancy);
		shelterService.addShelter(shelter);

		System.out.println("\nCadastro realizado com sucesso \n");
	}

	private static void showUpdateShelterMenu() {
		System.out.print("\nEnter Shelter ID to update:\n");
		int id = scanner.nextInt();
		scanner.nextLine();

		Shelter shelter = shelterService.findById(id);
		if (shelter == null) {
			System.out.println("Shelter not found.");
			return;
		}

		while (true) {
			System.out.println("Updating Shelter: " + shelter.getName());
			System.out.println("1. Update Name");
			System.out.println("2. Update Address");
			System.out.println("3. Update Contact");
			System.out.println("4. Update Capacity");
			System.out.println("5. Update Occupancy");
			System.out.println("6. Update email");
			System.out.println("7. Back to Shelters Menu");
			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				System.out.print("Enter new name: ");
				String name = scanner.nextLine();
				if (!name.isEmpty()) {
					shelter.setName(name);
				}
				break;
			case 2:
				System.out.print("Enter new address: ");
				String address = scanner.nextLine();
				if (!address.isEmpty()) {
					shelter.setAddress(address);
				}
				break;
			case 3:
				System.out.print("Enter new contact: ");
				String contact = scanner.nextLine();
				if (!contact.isEmpty()) {
					shelter.setPhoneNumber(contact);
				}
				break;
			case 4:
				System.out.print("Enter new capacity: ");
				int capacity = scanner.nextInt();
				if (capacity > 0) {
					shelter.setCapacity(capacity);
				}
				scanner.nextLine();
				break;
			case 5:
				System.out.print("Enter new occupancy: ");
				int occupancy = scanner.nextInt();
				if (occupancy >= 0) {
					shelter.setOccupancy(occupancy);
				}
				scanner.nextLine();
				break;
			case 6:
				System.out.print("Enter new email: ");
				String email = scanner.nextLine();
				if (!email.isEmpty()) {
					shelter.setEmail(email);
				}
				scanner.nextLine();
				break;
			case 7:
				shelterService.updateShelter(shelter, shelter.getId());
				System.out.println("\nShelter updated successfully.\n");
				return;
			default:
				System.out.println("\nInvalid option\n");
			}
		}
	}

	private static void showDonationsMenu() {
		while (true) {
			System.out.println("Donations Menu:");
			System.out.println("1. Add Donation Manually");
			System.out.println("2. Add Donation from CSV");
			System.out.println("3. List Donations");
			System.out.println("4. Back to Main Menu");
			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (option) {
			case 1:
				DonationsMenus.addDonationManually(scanner, distributionCenterService);
				break;
			case 2:
				addDonationFromCSV();
				break;
			case 3:
				// listDonations();
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid option");
			}
		}
	}

	

	private static void addDonationFromCSV() {
		System.out.print("Enter CSV file path: ");
		String csvFilePath = scanner.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String itemType = values[0];
				String itemName = values[1];
				int quantity = Integer.parseInt(values[2]);
				int distributionCenterId = Integer.parseInt(values[3]);

				DistributionCenter distributionCenter = distributionCenterService.findById(distributionCenterId);
				if (distributionCenter == null) {
					System.out.println("Distribution Center not found for ID: " + distributionCenterId);
					continue;
				}

			}
			System.out.println("Donations added from CSV successfully.");
		} catch (IOException e) {
			System.out.println("Error reading CSV file: " + e.getMessage());
		}
	}

	/*
	 * private static void listDonations() { List<Donation> donations =
	 * donationDAO.getAll(); for (Donation donation : donations) {
	 * System.out.println(donation); } }
	 */
}