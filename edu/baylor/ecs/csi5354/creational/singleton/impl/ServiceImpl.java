package edu.baylor.ecs.csi5354.creational.singleton.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.baylor.ecs.csi5354.creational.singleton.Logger;
import edu.baylor.ecs.csi5354.creational.singleton.Service;

public class ServiceImpl implements Service {

	private static ServiceImpl instance;

	private static Lock lock = new ReentrantLock();

	Logger log = LoggerImpl.getInstance(ServiceImpl.class);

	/**
	 *
	 * Applies Singleton and always returns the same instance. The method uses locks so that creation is thread safe.
	 * @return An instance of the ServiceImpl.
	 *
	 */
	public static ServiceImpl getInstance(){
		if (instance == null){
			lock.lock();
			try{
				if (instance == null) {
					instance = new ServiceImpl();
				}
			}
			finally{
				lock.unlock();
			}
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
		Register.getInstance().add(now(), id, amount); // it could be better
		log.log("registerSale " + id);
	}

	public double dailyReport(Date day) {
		sleep(1000);
		log.log("dailyReport");
		return Register.getInstance().total(now()); // it could be better
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
