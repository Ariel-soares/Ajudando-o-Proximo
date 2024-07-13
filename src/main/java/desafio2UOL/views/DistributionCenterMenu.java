package desafio2UOL.views;

import java.util.List;
import java.util.Scanner;

import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Order;
import desafio2UOL.entities.Shelter;
import desafio2UOL.services.DistributionCenterService;
import desafio2UOL.services.OrderService;
import desafio2UOL.services.ShelterService;
import jakarta.persistence.EntityManager;

public class DistributionCenterMenu {

	public static void showDistributionCenterMenu(Scanner scanner, EntityManager em) {
		DistributionCenterService service = new DistributionCenterService();

		while (true) {
			System.out.println("\nDistribution Center Menu:\n");
			System.out.println("1. Add Distribution Center");
			System.out.println("2. List Distribution Centers");
			System.out.println("3. Manage distribution center request orders");
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
				attendOrderRequests(scanner, service, em);
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

	private static void attendOrderRequests(Scanner scanner, DistributionCenterService service, EntityManager em) {
		OrderService orderService = new OrderService();

		List<DistributionCenter> centers = service.getAllDistributionCenters(em);
		System.out.println("\n-------------- Distribution Centers available -----------------\n");
		for (DistributionCenter center : centers) {
			System.out.println("\n" + center);
		}
		System.out.println("Enter desired DistributionCenter Id:\n");
		Integer id = scanner.nextInt();
		scanner.nextLine();

		DistributionCenter center = service.findById(id, em);

		if (center != null) {
			System.out.println(center);
			System.out.println("\nItems on distribution center " + center.getName() + "\n");
			System.out.println(center.getItems());

		} else {
			System.err.println("Distribution Center not found");
			return;
		}

		if (center.getOrders().isEmpty()) {
			return;
		}

		System.out.println("\nDo you want to attend the request orders from the shelters?\n   Y - YES / N - NOT \n");
		char answer = scanner.nextLine().toUpperCase().charAt(0);

		switch (answer) {
		case 'Y':
			System.out.println(center.getOrders());
			break;
		case 'N':
			System.out.println("Returning to main menu\n");
			return;
		}

		Order order = new Order();
		System.out.println("\nEnter order id for attending\n");
		int orderId = scanner.nextInt();
		scanner.nextLine();

		for (Order o : center.getOrders()) {
			if (o.getId() == orderId) {
				order = o;
			}
		}

		if (order.getId() == null) {
			System.err.println("Invalid id");
			return;
		}

		String[] values = order.getItemCode().split("/");

		if (center.getItems().containsKey(order.getItemCode())) {
			int value = center.getItems().get(order.getItemCode());
			if (value < order.getQuantity()) {
				System.out.println(
						"This distribution Center does not have enough units of this item, maybe later with more donations incoming");
				return;
			}

			if (values[0].toLowerCase().equals("food")) {
				System.out.println("  This shelter asks for " + order.getQuantity() + " unit(s) of " + values[1]);
			} else if (values[0].toLowerCase().equals("cloth")) {
				System.out.println("  This shelter asks for " + order.getQuantity() + " unit(s) of " + values[1]
						+ " of gender " + values[2] + " of size " + values[3]);
			} else if (values[0].toLowerCase().equals("hygiene")) {
				System.out.println("  This shelter asks for " + order.getQuantity() + " unit(s) of " + values[1]);
			}

			System.out.println("\nThis Distribution Center has " + value
					+ " units of this item\nDo you want to accept or deny this request? 1 - Accept/ 2 - Deny\n");
			int decision = scanner.nextInt();
			scanner.nextLine();

			switch (decision) {
			case 1:
				attendRequestOrder(order, center, values[0].toLowerCase(), em, orderService, service);
				break;
			case 2:

				System.out.println("Enter the deny reason\n");
				String reason = scanner.nextLine();

				order.setAttended(true);
				order.setAttendance(reason);
				orderService.updateOrder(order, orderId, em);

				break;
			}

		} else {
			System.out.println(
					" This distribution Center does not have this item yet, maybe later with more donations incoming");
			return;
		}

	}

	private static void attendRequestOrder(Order order, DistributionCenter center, String itemType, EntityManager em,
			OrderService orderService, DistributionCenterService service) {

		ShelterService shelterService = new ShelterService();
		Shelter shelter = shelterService.findById(order.getRequester().getId(), em);

		// -------------------------- Conferindo se o abrigo têm espaço para esse item
		// ---------------------------------

		Integer amount = order.getQuantity();

		if ((itemType.equals("food") && shelter.getFoodItems() >= 200)
				|| (itemType.equals("cloth") && shelter.getClothItems() >= 200)
				|| (itemType.equals("hygiene") && shelter.getHygieneItems() >= 200)) {
			System.out.println(
					"The maximum possible for this type of item has been reached on this shelter, try to attend this order later!\n");
			return;
		}

		if (itemType.equals("food")) {
			if (shelter.getFoodItems() + order.getQuantity() > 200) {
				System.out.println("This shelter can receive only " + (200 - shelter.getFoodItems())
						+ " units from this type of item.\n");
				order.setAttendance(
						"Order exceeds shelter's actual total capacity for this kind of item, we will be adding "
								+ (200 - shelter.getFoodItems()) + " units from the previous requested amount of "
								+ amount + " requested units");
				amount = 200 - shelter.getFoodItems();
			}
		} else if (itemType.equals("cloth")) {
			if (shelter.getClothItems() + order.getQuantity() > 200) {
				System.out.println("This shelter can receive only " + (200 - shelter.getClothItems())
						+ " units from this type of item.\n");
				order.setAttendance(
						"Order exceeds shelter's actual total capacity for this kind of item, we will be adding "
								+ (200 - shelter.getClothItems()) + " units from the previous requested amount of "
								+ amount + " requested units");
				amount = 200 - shelter.getFoodItems();
			}
		} else if (itemType.equals("hygiene")) {
			if (shelter.getHygieneItems() + order.getQuantity() > 200) {
				System.out.println("This shelter can receive only " + (200 - shelter.getHygieneItems())
						+ " units from this type of item.\n");
				order.setAttendance(
						"Order exceeds shelter's actual total capacity for this kind of item, we will be adding "
								+ (200 - shelter.getHygieneItems()) + " units from the previous requested amount of "
								+ amount + " requested units");
				amount = 200 - shelter.getHygieneItems();
			}
		}

		// -------------------------- Conferindo se o abrigo têm espaço para esse item
		// ---------------------------------

		Integer quantitysoFar = center.getItems().get(order.getItemCode());
		center.getItems().put(order.getItemCode(), (quantitysoFar - amount));

		if (shelter.getItems().containsKey(order.getItemCode())) {
			Integer quantityInShelter = shelter.getItems().get(order.getItemCode());
			shelter.getItems().put(order.getItemCode(), quantityInShelter + amount);
		} else {
			shelter.getItems().put(order.getItemCode(), amount);
		}

		if (itemType.equals("food")) {
			center.setFoodItems(center.getFoodItems() - amount);
			shelter.setFoodItems(shelter.getFoodItems() + amount);
			System.out.println("Food items amount decreased, actual amount: " + center.getFoodItems());
		} else if (itemType.equals("cloth")) {
			center.setClothItems(center.getClothItems() - amount);
			shelter.setClothItems(shelter.getClothItems() + amount);
			System.out.println("Cloth items amount decreased, actual amount: " + center.getClothItems());
		} else if (itemType.equals("hygiene")) {
			center.setHygieneItems(center.getHygieneItems() - amount);
			shelter.setHygieneItems(shelter.getHygieneItems() + amount);
			System.out.println("Hygiene items amount decreased, actual amount: " + center.getHygieneItems());
		}

		shelterService.updateShelter(shelter, shelter.getId(), em);

		service.updateDistributionCenter(center, center.getId(), em);
		System.out.println("\nOrder request attended succesfully");

		order.setAttendance("Order attended, " + amount + " items sent to the requester");

		order.setAttended(true);
		orderService.updateOrder(order, order.getId(), em);
	}

}
