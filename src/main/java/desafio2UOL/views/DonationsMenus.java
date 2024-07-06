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
import desafio2UOL.entities.Item;
import desafio2UOL.services.DistributionCenterService;
import desafio2UOL.services.ItemService;

public class DonationsMenus {

	public static void showDonationsMenu(Scanner scanner, DistributionCenterService distributionCenterService,
			ItemService itemService) {
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
				DonationsMenus.addDonationManually(scanner, distributionCenterService, itemService);
				break;
			case 2:
				// DonationsMenus.addDonationFromCSV(scanner, distributionCenterService);
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

	public static void addDonationManually(Scanner scanner, DistributionCenterService distributionCenterService, ItemService itemService) {
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

				case 2:
					items.add(new ClothItem(null, name, "cloth", 'F', size));
				}

			case 2:
				;
				break;
			case 3:
				;
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
					for (Item i : donation.getItens()) {
						itemService.addItem(i);
					}
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

	private static void addDonationFromCSV(Scanner scanner, DistributionCenterService distributionCenterService) {
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

}
