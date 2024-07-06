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
import desafio2UOL.views.ShelterMenus;
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
		
		showMenu();
		

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
				DonationsMenus.showDonationsMenu(scanner, distributionCenterService);;
				break;
			case 2:
				// listDonations();
				break;
			case 3:
				ShelterMenus.showShelterMenu(scanner, shelterService);
				break;
			case 4:
				DistributionCenter cd2 = distributionCenterService.findById(1);
				System.out.println(cd2.getItems());
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
			}
		}
	}

	

	

	

	/*
	 * private static void listDonations() { List<Donation> donations =
	 * donationDAO.getAll(); for (Donation donation : donations) {
	 * System.out.println(donation); } }
	 */
}