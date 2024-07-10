package desafio2UOL.views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import desafio2UOL.entities.ClothItem;
import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Donation;
import desafio2UOL.entities.FoodItem;
import desafio2UOL.entities.HygieneItem;
import desafio2UOL.entities.Item;
import desafio2UOL.entities.enums.ItemName;
import desafio2UOL.entities.enums.ItemType;
import desafio2UOL.services.DistributionCenterService;
import desafio2UOL.services.DonationService;
import desafio2UOL.services.ItemService;
import jakarta.persistence.EntityManager;

public class DonationsMenus {

	public static void showDonationsMenu(Scanner scanner, DistributionCenterService distributionCenterService,
			ItemService itemService, DonationService donationService, EntityManager em) {
		while (true) {
			System.out.println("Donations Menu:");
			System.out.println("1. Add Donation Manually");
			System.out.println("2. Add Donation from CSV");
			System.out.println("3. List Donations");
			System.out.println("4. Back to Main Menu");
			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				DonationsMenus.addDonationManually(scanner, distributionCenterService, itemService, donationService,
						em);
				break;
			case 2:
				addDonationFromCSV(scanner, distributionCenterService, itemService, donationService, em);
				break;
			case 3:
				listDonations(donationService, em);
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid option");
			}
		}
	}

	private static void listDonations(DonationService donationService, EntityManager em) {
		List<Donation> donations = donationService.getAllDonations(em);
		System.out.println(donations);
	}

	public static void addDonationManually(Scanner scanner, DistributionCenterService distributionCenterService,
			ItemService itemService, DonationService donationService, EntityManager em) {

		Donation donation = new Donation();
		List<Item> items = new ArrayList<>();

		while (true) {
			System.out.print("Enter item type (1 - Clothes/2 - Hygiene/3 - Food/ 4 - End Donation): ");
			Integer itemType = scanner.nextInt();
			scanner.nextLine();

			switch (itemType) {
			case 1:
				System.out.println("Enter quantity to be added:");
				Integer quantity = scanner.nextInt();
				System.out.println("Enter item name: ");
				String name = scanner.nextLine();
				System.out.println("Enter product size (Infantil/PP/P/M/G/GG)");
				String size = scanner.next().toUpperCase();
				System.out.println("Enter item gender: (1 - Masculine / 2 - Feminine)");
				Integer option = scanner.nextInt();
				switch (option) {
				case 1:
					items.add(new ClothItem(ItemName.valueOf(name.toUpperCase()), "cloth", 'M', size));
					break;

				case 2:
					items.add(new ClothItem(ItemName.valueOf(name.toUpperCase()), "cloth", 'F', size));
					break;
				}
				break;
			case 2:
				System.out.println("Enter quantity to be added:");
				quantity = scanner.nextInt();
				System.out.println("Enter item name: ");
				name = scanner.nextLine();
				System.out.println("Enter item description: ");
				String description = scanner.nextLine();
				items.add(new HygieneItem(ItemName.valueOf(name.toUpperCase()), description));
				break;
			case 3:
				System.out.println("Enter quantity to be added:");
				quantity = scanner.nextInt();
				System.out.println("Enter item name: ");
				name = scanner.nextLine();
				System.out.println("Enter item description: ");
				description = scanner.nextLine();
				System.out.println("Enter the item measurement: ");
				String measurement = scanner.nextLine();
				System.out.println("Enter item validity date: ");
				String validity = scanner.nextLine();
				items.add(new FoodItem(ItemName.valueOf(name.toUpperCase()), description, measurement, validity));
				break;
			case 4:
				List<DistributionCenter> distributionCenters = distributionCenterService.getAllDistributionCenters(em);
				System.out.println("Available Distribution Centers:");
				for (DistributionCenter dc : distributionCenters) {
					System.out.println(dc.getId() + ": " + dc.getName());
				}

				System.out.print("\nEnter distribution center ID: \n");
				int distributionCenterId = scanner.nextInt();
				scanner.nextLine();

				DistributionCenter distributionCenter = distributionCenterService.findById(distributionCenterId, em);

				if (distributionCenter != null) {

					itemService.addItemList(items, em);

					donation.setCenterId(distributionCenter);
					donationService.addDonation(donation, em);
					//distributionCenter.getDonations().add(donation);
					// distributionCenterService.addDonation(donation, distributionCenterId, em);
					System.out.println("\nDonation added\n");
					return;
				} else
					System.out.println("Distribution Center not found.");

				return;
			default:
				System.out.println("Invalid option");
			}
		}
	}

	private static void addDonationFromCSV(Scanner scanner, DistributionCenterService distributionCenterService,
			ItemService itemService, DonationService donationService, EntityManager em) {

		System.out.print("Enter CSV file path: ");
		String csvFilePath = scanner.nextLine();
		List<Donation> donations = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				int distributionCenterId = Integer.parseInt(values[0]);
				String itemType = values[1];

				Item item = createItemFromCSV(itemType, values);
				itemService.addItem(item, em);
				DistributionCenter distributionCenter = distributionCenterService.findById(distributionCenterId, em);
				if (item == null) {
					System.out.println("Invalid item type in CSV: " + itemType);
					continue;
				}

				donations.add(new Donation(distributionCenter, Integer.parseInt(values[2]), item));
			}

			for (Donation d : donations) {

				donationService.addDonation(d, em);

				DistributionCenter distributionCenter = distributionCenterService.findById(d.getCenterId().getId(), em);
				if (distributionCenter == null) {
					System.out.println("Distribution Center not found: " + d.getCenterId().getId());
					continue;
				}
				
				Item i = d.getItem();
				
				if(distributionCenter.getItems().containsKey(i)) {
					Integer quantitysoFar = distributionCenter.getItems().get(i);
					distributionCenter.getItems().put(i, quantitysoFar + d.getQuantity());
				}else {
					distributionCenter.getItems().put(i, d.getQuantity());
				}
				distributionCenter.getDonations().add(d);
				
				System.out.println(distributionCenter.getItems());
				distributionCenterService.updateDistributionCenter(distributionCenter, d.getCenterId().getId(), em);
			}

			System.out.println("Donations added from CSV successfully.\n");
		} catch (IOException e) {
			System.out.println("Error reading CSV file: " + e.getMessage());
		}
	}

	private static Item createItemFromCSV(String itemType, String[] values) {
		switch (itemType.toLowerCase()) {
		case "cloth":
			ClothItem cloth = new ClothItem();
			cloth.setId(null);
			cloth.setName(ItemName.valueOf(values[3].toUpperCase()));
			cloth.setDescription(values[4]);
			cloth.setGender(values[5].toUpperCase().charAt(0));
			cloth.setSize(values[6]);
			cloth.setItemType(ItemType.CLOTH);
			return cloth;
		case "food":
			FoodItem food = new FoodItem();
			food.setId(null);
			food.setDescription(values[3]);
			food.setMeasurement(values[4]);
			food.setValidity(values[5]);
			food.setItemType(ItemType.FOOD);
			return food;
		case "hygiene":
			HygieneItem hygiene = new HygieneItem();
			hygiene.setName(ItemName.valueOf(values[3].toUpperCase()));
			hygiene.setDescription(values[4]);
			hygiene.setItemType(ItemType.HYGIENE);
			return hygiene;
		default:
			return null;
		}
	}

}
