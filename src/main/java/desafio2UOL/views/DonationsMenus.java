package desafio2UOL.views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import desafio2UOL.entities.ClothItem;
import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Donation;
import desafio2UOL.entities.FoodItem;
import desafio2UOL.entities.HygieneItem;
import desafio2UOL.entities.Item;
import desafio2UOL.services.DistributionCenterService;
import desafio2UOL.services.DonationService;
import desafio2UOL.services.ItemService;

public class DonationsMenus {

	public static void showDonationsMenu(Scanner scanner, DistributionCenterService distributionCenterService,
			ItemService itemService, DonationService donationService) {
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
				DonationsMenus.addDonationManually(scanner, distributionCenterService, itemService, donationService);
				break;
			case 2:
				addDonationFromCSV(scanner, distributionCenterService, itemService, donationService);
				break;
			case 3:
				listDonations(donationService);
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid option");
			}
		}
	}

	private static void listDonations(DonationService donationService) {
		List<Donation> donations = donationService.getAllDonations();
		System.out.println(donations);
	}

	public static void addDonationManually(Scanner scanner, DistributionCenterService distributionCenterService,
			ItemService itemService, DonationService donationService) {

		Donation donation = new Donation();
		List<Item> items = new ArrayList<>();

		while (true) {
			System.out.print("Enter item type (1 - Clothes/2 - Hygiene/3 - Food/ 4 - End Donation): ");
			Integer itemType = scanner.nextInt();
			scanner.nextLine();

			switch (itemType) {
			case 1:
				System.out.println("Enter item name: ");
				String name = scanner.nextLine();
				System.out.println("Enter product size (Infantil/PP/P/M/G/GG)");
				String size = scanner.next().toUpperCase();
				System.out.println("Enter item gender: (1 - Masculine / 2 - Feminine)");
				Integer option = scanner.nextInt();
				switch (option) {
				case 1:
					items.add(new ClothItem(null, name, "cloth", 'M', size));
					break;

				case 2:
					items.add(new ClothItem(null, name, "cloth", 'F', size));
					break;
				}
				break;
			case 2:
				System.out.println("Enter item name: ");
				name = scanner.nextLine();
				System.out.println("Enter item description: ");
				String description = scanner.nextLine();
				items.add(new HygieneItem(null, name, description));
				break;
			case 3:
				System.out.println("Enter item description: ");
				description = scanner.nextLine();
				System.out.println("Enter the item measurement: ");
				String measurement = scanner.nextLine();
				System.out.println("Enter item validity date: ");
				String validity = scanner.nextLine();
				items.add(new FoodItem(null, description, measurement, validity));
				break;
			case 4:

				for (Item i : items) {
					donation.getItens().add(i);
				}

				List<DistributionCenter> distributionCenters = distributionCenterService.getAllDistributionCenters();
				System.out.println("Available Distribution Centers:");
				for (DistributionCenter dc : distributionCenters) {
					System.out.println(dc.getId() + ": " + dc.getName());
				}

				System.out.print("\nEnter distribution center ID: \n");
				int distributionCenterId = scanner.nextInt();
				scanner.nextLine();

				DistributionCenter distributionCenter = distributionCenterService.findById(distributionCenterId);

				if (distributionCenter != null) {

					itemService.addItemList(items);

					donation.setCenterId(distributionCenter);
					donationService.addDonation(donation);
					distributionCenter.getDonations().add(donation);
					distributionCenterService.addDonation(donation, distributionCenterId);
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

	private static void addDonationFromCSV(Scanner scanner, DistributionCenterService distributionCenterService, ItemService itemService, DonationService donationService) {

		System.out.print("Enter CSV file path: ");
		String csvFilePath = scanner.nextLine();
		List<Item> items = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String itemType = values[0];
				Item item = createItemFromCSV(itemType, values);

				if (item == null) {
					System.out.println("Invalid item type in CSV: " + itemType);
					continue;
				}
				items.add(item);
			}

			System.out.println("CSV file read successfully. Now, select the distribution center.");
			List<DistributionCenter> distributionCenters = distributionCenterService.getAllDistributionCenters();
			System.out.println("Available Distribution Centers:");
			for (DistributionCenter dc : distributionCenters) {
				System.out.println(dc.getId() + ": " + dc.getName());
			}
			System.out.print("Enter distribution center ID: ");
			int distributionCenterId = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			DistributionCenter distributionCenter = distributionCenterService.findById(distributionCenterId);
			
			if (distributionCenter == null) {
				System.out.println("Distribution Center not found.");
				return;
			}
			
			itemService.addItemList(items);
			Donation donation = new Donation(null, distributionCenter);
			for(Item i : items) {
				donation.addItem(i);
			}
			donationService.addDonation(donation);
			

			System.out.println("Donations added from CSV successfully.");
		} catch (IOException e) {
			System.out.println("Error reading CSV file: " + e.getMessage());
		}
	}

	private static Item createItemFromCSV(String itemType, String[] values) {

		switch (itemType.toLowerCase()) {
		case "cloth":
			ClothItem cloth = new ClothItem();
			cloth.setId(null);
			cloth.setName(values[1]);
			cloth.setDescription(values[2]);
			cloth.setGender(values[3].toUpperCase().charAt(0));
			cloth.setSize(values[4]);
			return cloth;

		case "food":
			FoodItem food = new FoodItem();
			food.setId(null);
			food.setDescription(values[1]);
			food.setMeasurement(values[2]);
			food.setValidity(values[3]);
			return food;

		case "hygiene":
			HygieneItem hygiene = new HygieneItem();
			hygiene.setName(values[1]);
			hygiene.setDescription(values[3]);
			return hygiene;

		default:
			return null;
		}

	}

	
}
