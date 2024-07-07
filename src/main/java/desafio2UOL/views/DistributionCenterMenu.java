package desafio2UOL.views;

import java.util.List;

import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.services.DistributionCenterService;
import jakarta.persistence.EntityManager;

public class DistributionCenterMenu {
	
	public static void showDistributionCenterMenu(EntityManager em) {
		DistributionCenterService service = new DistributionCenterService();
		
		List<DistributionCenter> centers = service.getAllDistributionCenters(em);
		
		System.out.println(centers);
		
		for(DistributionCenter cd : centers) {
			System.out.println(cd.getItems());
		}
		
	}

}
