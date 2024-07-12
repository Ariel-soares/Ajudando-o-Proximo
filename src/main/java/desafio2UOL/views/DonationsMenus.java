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
				addDonationManually(scanner, distributionCenterService, itemService, donationService, em);
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
		Item item;

		List<DistributionCenter> distributionCenters = distributionCenterService.getAllDistributionCenters(em);
		System.out.println("Available Distribution Centers for donation:");
		for (DistributionCenter dc : distributionCenters) {
			System.out.println(dc.getId() + ": " + dc.getName());
		}

		System.out.print("\nEnter distribution center ID: \n");
		int distributionCenterId = scanner.nextInt();
		scanner.nextLine();

		DistributionCenter distributionCenter = distributionCenterService.findById(distributionCenterId, em);

		donation.setCenterId(distributionCenter);
		System.out.print("Enter item type (1 - Clothes/2 - Hygiene/3 - Food/ 4 - End Donation): ");
		Integer itemType = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter quantity to be added:");
		Integer quantity = scanner.nextInt();

		donation.setQuantity(quantity);

		switch (itemType) {
		case 1:
			scanner.nextLine();

			System.out.println("Enter item name: ");
			String name = scanner.nextLine();
			System.out.println("Enter product size (Infantil/PP/P/M/G/GG)");
			String size = scanner.next().toUpperCase();
			System.out.println("Enter item gender: (1 - Masculine / 2 - Feminine)");
			Integer option = scanner.nextInt();
			switch (option) {
			case 1:
				item = new ClothItem(ItemName.valueOf(name.toUpperCase()), "cloth", 'M', size);
				item.setItemType(ItemType.CLOTH);
				donation.setItem(item);
				itemService.addItem(donation.getItem(), em);
				addDonation(donation, em, distributionCenterService, donationService, itemService);
				break;
			case 2:
				item = new ClothItem(ItemName.valueOf(name.toUpperCase()), "cloth", 'F', size);
				item.setItemType(ItemType.CLOTH);
				donation.setItem(item);
				addDonation(donation, em, distributionCenterService, donationService, itemService);
				break;
			}
			break;
		case 2:
			System.out.println("Enter item name: ");
			name = scanner.nextLine();
			System.out.println("Enter item description: ");
			String description = scanner.nextLine();
			item = new HygieneItem(ItemName.valueOf(name.toUpperCase()), description);
			item.setItemType(ItemType.HYGIENE);
			donation.setItem(item);
			break;
		case 3:
			System.out.println("Enter item name: ");
			name = scanner.nextLine();
			System.out.println("Enter item description: ");
			description = scanner.nextLine();
			System.out.println("Enter the item measurement: ");
			String measurement = scanner.nextLine();
			System.out.println("Enter item validity date: ");
			String validity = scanner.nextLine();

			item = new FoodItem(ItemName.valueOf(name.toUpperCase()), description, measurement, validity);
			item.setItemType(ItemType.FOOD);
			donation.setItem(item);
			break;
		case 4:

			if (donation.getItem() == null) {
				return;
			}

			if (distributionCenter != null) {
				donation.setCenterId(distributionCenter);
				addDonation(donation, em, distributionCenterService, donationService, itemService);
				return;

			} else
				System.out.println("Distribution Center not found.");

			return;
		default:
			System.out.println("Invalid option");
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
				// itemService.addItem(item, em);
				DistributionCenter distributionCenter = distributionCenterService.findById(distributionCenterId, em);
				if (item == null) {
					System.out.println("Invalid item type in CSV: " + itemType);
					continue;
				}

				donations.add(new Donation(distributionCenter, Integer.parseInt(values[2]), item));
			}

			for (Donation d : donations) {
				addDonation(d, em, distributionCenterService, donationService, itemService);
			}

		} catch (IOException e) {
			System.out.println("Error reading CSV file: " + e.getMessage());
		}
	}

	private static void addDonation(Donation donation, EntityManager em,
			DistributionCenterService distributionCenterService, DonationService donationService,
			ItemService itemService) {
		
		DistributionCenter distributionCenter = distributionCenterService
				.findById(donation.getDistributionCenter().getId(), em);
		if (distributionCenter == null) {
			System.out.println("Distribution Center not found: " + donation.getDistributionCenter().getId());
			return;
		}
		
		String[] values = donation.getItem().storageCode().split("/");
		
		if(values[0].toLowerCase().equals("food") && distributionCenter.getFoodItems() < 1000) {
			if(distributionCenter.getFoodItems() + donation.getQuantity() > 1000) {
				System.out.println("This distributionCenter can receive only " + (1000 - distributionCenter.getFoodItems()));
			}
		}else if(values[0].toLowerCase().equals("cloth") && distributionCenter.getClothItems() < 1000) {
			if(distributionCenter.getClothItems() + donation.getQuantity() > 1000) {
				System.out.println("This distributionCenter can receive only " + (1000 - distributionCenter.getClothItems()));
			}
		}else if(values[0].toLowerCase().equals("hygiene") && distributionCenter.getHygieneItems() < 1000) {
			if(distributionCenter.getHygieneItems() + donation.getQuantity() > 1000) {
				System.out.println("This distributionCenter can receive only " + (1000 - distributionCenter.getHygieneItems()));
			}
		}

		itemService.addItem(donation.getItem(), em);
		donationService.addDonation(donation, em);

		Item i = donation.getItem();

		if (distributionCenter.getItems().containsKey(i.storageCode())) {
			Integer quantitysoFar = distributionCenter.getItems().get(i.storageCode());
			distributionCenter.getItems().put(i.storageCode(), quantitysoFar + donation.getQuantity());
		} else {
			distributionCenter.getItems().put(i.storageCode(), donation.getQuantity());
		}
		distributionCenter.getDonations().add(donation);
		
		
		
		if(values[0].toLowerCase().equals("food")) {
			distributionCenter.setFoodItems(distributionCenter.getFoodItems() + donation.getQuantity());
		}else if(values[0].toLowerCase().equals("cloth")) {
			distributionCenter.setClothItems(distributionCenter.getClothItems() + donation.getQuantity());
		}else if(values[0].toLowerCase().equals("hygiene")) {
			distributionCenter.setHygieneItems(distributionCenter.getHygieneItems() + donation.getQuantity());
			System.out.println("aqui 3");
		}
		
		distributionCenterService.updateDistributionCenter(distributionCenter, donation.getDistributionCenter().getId(),
				em);
		
		System.out.println("Donations added from CSV successfully.\n");
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
			food.setName(ItemName.valueOf(values[3].toUpperCase()));
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
