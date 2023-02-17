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

	/**
	 *
	 * Applies Singleton and always returns the same instance. The method uses locks so that creation is thread safe.
	 * @return An instance of the SaleCounter.
	 *
	 */
	public static SaleCounter getInstance(){
		if (instance == null){
			lock.lock();
			try {
				if (instance == null) {
					instance = new SaleCounter();
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
	 * Increases the counter value. Implemented so that increasing the counter is thread-safe in the singleton instance.
	 * @return The increased counter.
	 *
	 */
	public int increaseCounter(){
		lock.lock();
		try {
			counter++;
		}
		finally {
			lock.unlock();
		}
		return counter;
	}
}
