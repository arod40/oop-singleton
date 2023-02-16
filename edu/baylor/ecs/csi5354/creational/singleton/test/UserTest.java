package edu.baylor.ecs.csi5354.creational.singleton.test;


import org.junit.jupiter.api.Test;

import edu.baylor.ecs.csi5354.creational.singleton.SaleCounter;
import edu.baylor.ecs.csi5354.creational.singleton.Service;
import edu.baylor.ecs.csi5354.creational.singleton.impl.ServiceImpl;

public class UserTest {

	private static final int USERS = 10;

	public static class User implements Runnable {

		@Override
		public void run() {
			Service service = new ServiceImpl();
			Double amount = 100.0;
			amount = service.taxCalculation(amount);
			service.registerSale(SaleCounter.counter++, amount);
		}

	}

	@Test
	public void testOne() throws InterruptedException {
		User u = new User();
		u.run();

		Service service = new ServiceImpl();
		System.out.println(service.dailyReport(service.now()));

	}
    // fix me..
	@Test
	public void testMany() throws InterruptedException {
		for (int i = 1; i < USERS + 1; i++) {
			Thread t = new Thread(new User());
			t.start();
		}

		Thread.sleep(USERS * 1000);

		Service service = new ServiceImpl(); // this is so ugly
		System.out.println(service.dailyReport(service.now()));

	}

}
