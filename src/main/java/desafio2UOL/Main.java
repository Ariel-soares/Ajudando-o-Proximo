package desafio2UOL;

import desafio2UOL.entities.ClothItem;
import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.entities.Donation;
import desafio2UOL.entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("desafio-context");
		EntityManager em = emf.createEntityManager();
		DistributionCenter cd = new DistributionCenter(null, "cd1", "rua 1", "campinas", "RS", "25642");

		em.getTransaction().begin();
		em.persist(cd);
		em.getTransaction().commit();

		Item cloth = new ClothItem(null, "Camisa", "Camisa Masculina branca", 'M', "M");
		Item cloth2 = new ClothItem(null, "Moletom", "Moletom feminino preto", 'F', "M");

		em.getTransaction().begin();
		em.persist(cloth);
		em.persist(cloth2);
		em.getTransaction().commit();

		Donation donation = new Donation(null, cd);

		donation.addItem(cloth);
		donation.addItem(cloth2);

		em.getTransaction().begin();
		em.persist(donation);
		em.getTransaction().commit();

		System.out.println("Operação concluída");

		Donation result = em.find(Donation.class, 1);

		for (Item item : result.getItens()) {
			System.out.println(item);
		}
		emf.close();
		em.close();

	}

}