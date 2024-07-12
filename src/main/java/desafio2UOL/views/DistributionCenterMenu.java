package desafio2UOL.views;

import java.util.List;
import java.util.Scanner;

import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.services.DistributionCenterService;
import jakarta.persistence.EntityManager;

public class DistributionCenterMenu {

	public static void showDistributionCenterMenu(Scanner scanner, EntityManager em) {
		DistributionCenterService service = new DistributionCenterService();

		while (true) {
			System.out.println("\nDistribution Center Menu:\n");
			System.out.println("1. Add Distribution Center");
			System.out.println("2. List Distribution Centers");
			System.out.println("3. Find Distribution Center");
			System.out.println("4. Update existing distribution Center");
			System.out.println("5. Delete Distribution Center");
			System.out.println("6. Back to Main Menu\n");
			System.out.print("Choose an option: \n");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				addDistributionCenter(scanner, service, em);
				break;
			case 2:
				listDistributionCenters(service, em);
				break;
			case 3:
				findDistributionCenter(scanner, service, em);
				break;
			case 4:
				showDistributionCenterUpdateMenu(scanner, service, em);
				break;
			case 5:
				deleteDistributionCenter(scanner, service, em);
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid option");
			}
		}
	}

	private static void listDistributionCenters(DistributionCenterService distributionCenterService, EntityManager em) {
		List<DistributionCenter> distributionCenters = distributionCenterService.getAllDistributionCenters(em);
		for (DistributionCenter cd : distributionCenters) {
			System.out.println(cd);
		}
	}

	private static void addDistributionCenter(Scanner scanner, DistributionCenterService service, EntityManager em) {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter address: ");
		String address = scanner.nextLine();
		System.out.print("Enter city: ");
		String city = scanner.nextLine();
		System.out.print("Enter state: ");
		String state = scanner.nextLine();
		System.out.print("Enter CEP: ");
		String cep = scanner.nextLine();

		service.addDistributionCenter(new DistributionCenter(null, name, address, city, state, cep), em);

		System.out.println("\nDistributionCenter added succesfully\n");
	}

	private static void deleteDistributionCenter(Scanner scanner, DistributionCenterService service, EntityManager em) {
		System.out.println("Enter DistributionCenter Id for deleting:\n");
		Integer id = scanner.nextInt();
		service.deleteDistributionCenter(id, em);
		System.out.println("Deletion Complete");
	}

	private static void showDistributionCenterUpdateMenu(Scanner scanner, DistributionCenterService service,
			EntityManager em) {
		System.out.print("\nEnter Distribution Center ID to update:\n");
		int id = scanner.nextInt();
		scanner.nextLine();

		DistributionCenter center = service.findById(id, em);

		while (true) {
			System.out.println("Updating DistributionCenter: " + center.getName());
			System.out.println("1. Update Name");
			System.out.println("2. Update Address");
			System.out.println("3. Update City");
			System.out.println("4. Update State");
			System.out.println("5. Update Cep");
			System.out.println("6. Back to Distribution Center Menu");
			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				System.out.print("Enter new name: ");
				String name = scanner.nextLine();
				if (!name.isEmpty()) {
					center.setName(name);
				}
				break;
			case 2:
				System.out.print("Enter new address: ");
				String address = scanner.nextLine();
				if (!address.isEmpty()) {
					center.setAddress(address);
				}
				break;
			case 3:
				System.out.print("Enter new city: ");
				String city = scanner.nextLine();
				if (!city.isEmpty()) {
					center.setCity(city);
				}
				break;
			case 4:
				System.out.print("Enter new state: ");
				String state = scanner.nextLine();
				if (!state.isEmpty()) {
					center.setState(state);
				}
				scanner.nextLine();
				break;
			case 5:
				System.out.print("Enter new cep: ");
				String cep = scanner.nextLine();
				if (!cep.isEmpty()) {
					center.setCep(cep);
				}
				scanner.nextLine();
				break;
			case 6:
				service.updateDistributionCenter(center, id, em);
				System.out.println("\nDistributionCenter updated successfully.\n");
				return;
			default:
				System.out.println("\nInvalid option\n");
			}
		}

	}

	private static void findDistributionCenter(Scanner scanner, DistributionCenterService service, EntityManager em) {
		List<DistributionCenter> centers = service.getAllDistributionCenters(em);
		System.out.println("\n-------------- Distribution Centers available -----------------\n");
		for (DistributionCenter center : centers) {
			System.out.println(center);
		}
		System.out.println("Enter desired DistributionCenter Id:\n");
		Integer id = scanner.nextInt();

		DistributionCenter center = service.findById(id, em);

		if (center != null) {
			System.out.println(center);
			System.out.println("\nItems on distribution center " + center.getName() + "\n");
			System.out.println(center.getItems());

		} else {
			System.err.println("Distribution Center not found");
			return;
		}
		
		if(center.getOrders().isEmpty()) {
			return;
		}

		System.out.println("\nDo you want to attend the request orders from the shelters?\n");
		char answer = scanner.nextLine().toUpperCase().charAt(0);

		switch (answer) {
		case 'S':
			System.out.println(center.getOrders());
		case 'N':
			System.out.println("Returning to main menu\n");
			return;
		}
		
		System.out.println("Enter order id for attending");

	}
}
