package se.gozacke.logg;

import org.apache.log4j.Logger;

public class Log4J {
	private static final Logger log = Logger.getLogger(Log4J.class);
	
	public static void main(String[] args) {
//		log.info("Test message");
//		log.trace("Test message");
//		log.debug("Test message");
		try {
			throw new Exception("New Exception");
		} catch(Exception e) {
//			log.error(e, e);
			log.error(e);
		}
	}
}