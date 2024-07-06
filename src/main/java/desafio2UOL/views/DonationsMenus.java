package desafio2UOL.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import desafio2UOL.entities.ClothItem;
import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Donation;
import desafio2UOL.entities.Item;
import desafio2UOL.services.DistributionCenterService;

public class DonationsMenus {
	
	public static void addDonationManually(Scanner scanner, DistributionCenterService distributionCenterService) {
		
		
		
		Donation donation = new Donation();
		List<Item> items = new ArrayList<>();
		
		while (true) {
			System.out.print("Enter item type (1 - Clothes/2 - Hygiene/3 - Food/ 4 - End Donation): ");
			Integer itemType = scanner.nextInt();
			scanner.nextLine(); // Consume newline

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
				
				for(Item i : items) {
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
					distributionCenter.getDonations().add(donation);
					distributionCenterService.updateDistributionCenter(distributionCenter, distributionCenterId);
					System.out.println("\nDonation added\n");
					return;
				}else 
					System.out.println("Distribution Center not found.");
				
				return;
			default:
				System.out.println("Invalid option");
			}
		}
	}

}
