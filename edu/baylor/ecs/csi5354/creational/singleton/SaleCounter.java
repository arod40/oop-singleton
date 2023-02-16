package edu.baylor.ecs.csi5354.creational.singleton;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
