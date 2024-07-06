package desafio2UOL.views;

import java.util.Scanner;

import desafio2UOL.entities.Shelter;
import desafio2UOL.services.ShelterService;

public class ShelterMenus {

	public static void showShelterMenu(Scanner scanner, ShelterService shelterService) {
		while (true) {
			System.out.println("\n1. List Shelters");
			System.out.println("2. Find Specific Shelter");
			System.out.println("3. Add Shelter");
			System.out.println("4. Delete Shelter");
			System.out.println("5. Update Shelter");
			System.out.println("6. Order items from shelter");
			System.out.println("6. Exit");
			System.out.print("\nChoose an option: \n");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				System.out.println("\nLista de abrigos cadastrados\n");
				shelterService.listShelters();
				break;
			case 2:
				System.out.println("\nEnter Shelter Id:\n");
				Integer idForFound = scanner.nextInt();
				shelterService.findOne(idForFound);
				break;
			case 3:
				addShelter(scanner, shelterService);
				break;
			case 4:
				System.out.println("Enter Shelter Id: ");
				Integer id = scanner.nextInt();
				shelterService.deleteShelter(id);
				break;
			case 5:
				showUpdateShelterMenu(scanner, shelterService);
				break;
			case 6:
				System.out.println("\nImplementar função\n");
				break;
			case 7:
				System.out.println("\nReturning to main menu\n");
				return;
			default:
				System.out.println("\nInvalid option\n");
			}
		}
	}

	private static void addShelter(Scanner scanner, ShelterService shelterService) {
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

	private static void showUpdateShelterMenu(Scanner scanner, ShelterService shelterService) {
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

}
