package edu.baylor.ecs.csi5354.creational.singleton.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Register {
	private static Lock lock = new ReentrantLock();

	private Map<Date, Double> register = new HashMap<>();
	private Map<Date, Map<Integer, Double>> registerDetails = new HashMap<>();

	private static Register instance;

	protected Register(){}

	/**
	 *
	 * Applies Singleton and always returns the same instance. The method uses locks so that creation is thread safe.
	 * @return An instance of the Register.
	 *
	 */
	public static Register getInstance(){
		if (instance == null){
			lock.lock();
			try{
				if (instance == null) {
					instance = new Register();
				}
			}
			finally {
				lock.unlock();
			}
		}
		return instance;
	}

	/**
	 *
	 * Registers a sale. The method uses locks so that state modification is thread safe.
	 */
	public void add(Date date, int id, Double amount) {
		lock.lock();
		try {
			if (!register.containsKey(date)) {
				register.put(date, amount);
			} else {
				register.put(date, register.get(date) + amount);
			}

			if (!registerDetails.containsKey(date)) {
				Map<Integer, Double> registration = new HashMap<>();
				registerDetails.put(date, registration);
			}
			registerDetails.get(date).put(id, amount);
		}
		finally {
			lock.unlock();
		}
	}

	public Double total(Date date) {
		return register.get(date);
	}
}
