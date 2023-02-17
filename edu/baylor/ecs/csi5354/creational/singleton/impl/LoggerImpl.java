package edu.baylor.ecs.csi5354.creational.singleton.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.baylor.ecs.csi5354.creational.singleton.Logger;

public class LoggerImpl implements Logger {

	private static Map<Class<?>, LoggerImpl> instances = new HashMap<>();

	private static int counter = 1;
	private int id;
	private Class<?> c;
	private List<String> files = new ArrayList<>();

	/**
	 *
	 * Applies Singleton on the parameter c: always returns the same instance for a given class c.
	 * @param c The class for which the caller wants to obtain the Logger.
	 * @return An instance of the LoggerImpl.
	 */
	public static LoggerImpl getInstance(Class<?> c){
		if (!instances.containsKey(c)){
			instances.put(c, new LoggerImpl(c));
		}
		return instances.get(c);
	}

	protected LoggerImpl(Class<?> c) {
		this.c = c;
		id = counter++;
		log("Made logger "+id );
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fel.cs.ass.Logger#log(java.lang.String)
	 */
	@Override
	public void log(String s) {
		files.add(s);
		System.out.println("Logging [" + c.getSimpleName() + "#" + id + "]: " + s);
	}


}
