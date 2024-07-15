package desafio2UOL.views;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Donation;
import desafio2UOL.entities.Shelter;
import desafio2UOL.services.DistributionCenterService;
import desafio2UOL.services.DonationService;
import desafio2UOL.services.ShelterService;
import jakarta.persistence.EntityManager;

public class ReportMenu {

	public static void showReportMenu(Scanner scanner, DistributionCenterService distributionCenterService,
			ShelterService shelterService, DonationService donationService, EntityManager em) {
		
		
		
		while (true) {
			System.out.println("\nReport Menu:");
			System.out.println("1. Distribution center report");
			System.out.println("2. Shelter report");
			System.out.println("3. Donation report");
			System.out.println("4. Back to main menu");
			System.out.print("Choose an option: \n");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				generateDistributionCenterReport(distributionCenterService, em);
				break;
			case 2:
				generateShelterReport(shelterService, em);
				break;
			case 3:
				generateDonationReport(donationService, em);
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid option, try again.");
			}

		}
	}

	private static void generateDonationReport(DonationService donationService, EntityManager em) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		List<Donation> donations = donationService.getAllDonations(em);
        String fileName = "donations_report.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Donation donation : donations) {
                writer.write("ID: " + donation.getId());
                writer.newLine();
                writer.write("Time: " + donation.getTime().format(dtf));
                writer.newLine();
                writer.write("Item: " + donation.getItem());
                writer.newLine();
                writer.write("Quantity: " + donation.getQuantity());
                writer.newLine();
                writer.write("Observation: " + donation.getObservation());
                writer.newLine();
                writer.write("Distribution Center ID: " + donation.getDistributionCenter().getId());
                writer.newLine();
                writer.write("---------------------------------");
                writer.newLine();
            }
            System.out.println("Donation report generated.");
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
		
	}

	private static void generateShelterReport(ShelterService shelterService, EntityManager em) {
		List<Shelter> shelters = shelterService.getAllShelters(em);
        String fileName = "shelters_report.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Shelter shelter : shelters) {
                writer.write("ID: " + shelter.getId());
                writer.newLine();
                writer.write("Name: " + shelter.getName());
                writer.newLine();
                writer.write("Address: " + shelter.getAddress());
                writer.newLine();
                writer.write("Stock: " + shelter.getItems());
                writer.newLine();
                writer.write("Cloth Items: " + shelter.getClothItems());
                writer.newLine();
                writer.write("Food Items: " + shelter.getFoodItems());
                writer.newLine();
                writer.write("Hygiene Items: " + shelter.getHygieneItems());
                writer.newLine();
                writer.write("---------------------------------");
                writer.newLine();
            }
            System.out.println("Shelter report generated.");
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
		
	}

	private static void generateDistributionCenterReport(DistributionCenterService distributionCenterService, EntityManager em) {
		 List<DistributionCenter> centers = distributionCenterService.getAllDistributionCenters(em);
		 
		 String fileName = "distribution_centers_report.txt";
		 
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
	            for (DistributionCenter center : centers) {
	                writer.write("ID: " + center.getId());
	                writer.newLine();
	                writer.write("Name: " + center.getName());
	                writer.newLine();
	                writer.write("Address: " + center.getAddress());
	                writer.newLine();
	                writer.write("Stock: " + center.getItems());
	                writer.newLine();
	                writer.write("Cloth Items: " + center.getClothItems());
	                writer.newLine();
	                writer.write("Food Items: " + center.getFoodItems());
	                writer.newLine();
	                writer.write("Hygiene Items: " + center.getHygieneItems());
	                writer.newLine();
	                writer.write("---------------------------------");
	                writer.newLine();
	            }
	            System.out.println("Distribution Center Report generated.");
	        } catch (IOException e) {
	            System.out.println("Error generating report: " + e.getMessage());
	        }

	}

}
