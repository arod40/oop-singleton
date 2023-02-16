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

	public static Register getInstance(){
		if (instance == null){
			lock.lock();
			if (instance == null) {
				instance = new Register();
			}
			lock.unlock();
		}
		return instance;
	}


	public void add(Date date, int id, Double amount) {
		lock.lock();
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
		lock.unlock();
	}

	public Double total(Date date) {
		return register.get(date);
	}
}
