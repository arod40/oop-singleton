package edu.baylor.ecs.csi5354.creational.singleton.impl;
import java.util.ArrayList;
import java.util.List;

import edu.baylor.ecs.csi5354.creational.singleton.Logger;

public class LoggerImpl implements Logger {

	private static int counter = 1;
	private int id;
	private Class<?> c;
	private List<String> files = new ArrayList<>();

	public LoggerImpl(Class<?> c) {
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
