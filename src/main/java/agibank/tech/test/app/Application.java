package agibank.tech.test.app;

import agibank.tech.test.service.BusinessService;

public class Application {

	public static void main(String[] args) {
		try {
			System.out.println("Starting service");
			BusinessService service = new BusinessService();
			service.check();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}