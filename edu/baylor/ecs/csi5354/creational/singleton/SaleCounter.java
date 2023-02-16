package edu.baylor.ecs.csi5354.creational.singleton;

import edu.baylor.ecs.csi5354.creational.singleton.impl.ServiceImpl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Helper class
 */
public class SaleCounter {
	private static SaleCounter instance;
	private static Lock lock = new ReentrantLock();

	private int counter = 0;

	protected SaleCounter(){}

	public static SaleCounter getInstance(){
		if (instance == null){
			lock.lock();
			if (instance == null){
				instance = new SaleCounter();
			}
			lock.unlock();
		}
		return instance;
	}

	public int increaseCounter(){
		lock.lock();
		counter++;
		lock.unlock();
		return counter;
	}
}
