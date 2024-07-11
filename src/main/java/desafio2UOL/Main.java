package desafio2UOL;

import java.util.Scanner;

import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Shelter;
import desafio2UOL.services.DistributionCenterService;
import desafio2UOL.services.DonationService;
import desafio2UOL.services.ItemService;
import desafio2UOL.services.OrderService;
import desafio2UOL.services.ShelterService;
import desafio2UOL.views.DistributionCenterMenu;
import desafio2UOL.views.DonationsMenus;
import desafio2UOL.views.ShelterMenus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static ShelterService shelterService = new ShelterService();
	private static DistributionCenterService distributionCenterService = new DistributionCenterService();
	private static ItemService itemService = new ItemService();
	private static DonationService donationService = new DonationService();
	private static OrderService orderService = new OrderService();

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");
		EntityManager em = emf.createEntityManager();

		DistributionCenter cd = new DistributionCenter(null, "centro 1", "rua 1", "campinas", "RS", "25642");
		DistributionCenter cd2 = new DistributionCenter(null, "centro 2", "rua 1", "campinas", "RS", "25642");

		em.getTransaction().begin();
		em.persist(cd);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(cd2);
		em.getTransaction().commit();
		
		Shelter shelter = new Shelter();
		
		em.getTransaction().begin();
		em.persist(shelter);
		em.getTransaction().commit();

		showMenu(em);

		emf.close();
		em.close();

	}

	private static void showMenu(EntityManager em) {
		while (true) {
			System.out.println("\n1. Donation Management");
			System.out.println("2. Shelter Management");
			System.out.println("3. Distribution Center Management");
			System.out.println("4. Exit");
			System.out.print("\nChoose an option:\n");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				DonationsMenus.showDonationsMenu(scanner, distributionCenterService, itemService, donationService, em);
				break;
			case 2:
				ShelterMenus.showShelterMenu(scanner, shelterService, em, distributionCenterService, orderService);
				break;
			case 3:
				DistributionCenterMenu.showDistributionCenterMenu(scanner, em);
				break;
			case 4:
				System.out.println("\nENCERRANDO SISTEMA");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
			}
		}
	}
}