package edu.baylor.ecs.csi5354.creational.singleton.impl;

import java.util.Calendar;
import java.util.Date;

import edu.baylor.ecs.csi5354.creational.singleton.Logger;
import edu.baylor.ecs.csi5354.creational.singleton.Service;

public class ServiceImpl implements Service {

	private static ServiceImpl instance;

	Logger log = new LoggerImpl(ServiceImpl.class);

	public static ServiceImpl getInstance(){
		if (instance == null){
			instance = new ServiceImpl();
		}
		return instance;
	}

	protected ServiceImpl() {
		sleep(2000);
		log.log("initiated");
	}

	public double taxCalculation(double amount) {
		sleep(200);
		log.log("taxCalculation");
		return amount * 1.21;
	}

	public void registerSale(int id, double amount) {
		// issue registration
		sleep(50);
		Register.instance.add(now(), id, amount); // it could be better
		log.log("registerSale " + id);
	}

	public double dailyReport(Date day) {
		sleep(1000);
		log.log("dailyReport");
		return Register.instance.total(now()); // it could be better
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // simulation
	}

	public Date now() {
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
