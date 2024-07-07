package desafio2UOL.views;

import desafio2UOL.entities.DistributionCenter;
import desafio2UOL.services.DistributionCenterService;

public class DistributionCenterMenu {
	
	public static void showDistributionCenterMenu() {
		DistributionCenterService service = new DistributionCenterService();
		
		DistributionCenter center = service.findById(1);
		
		System.out.println(center);
		
	}

}
