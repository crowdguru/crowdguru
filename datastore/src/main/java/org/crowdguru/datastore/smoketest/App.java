package org.crowdguru.datastore.smoketest;

import static org.crowdguru.datastore.context.Singletons.guruRepository;

import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.repositories.GuruRepository;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new App().run();
	}
	
	private void run() {
		GuruRepository guruRepository = guruRepository();
		log().info("Got guru repository '" + guruRepository + "'");
		Guru guru = new Guru("GURU_FORENAME", "GURU_SURNAME");
		log().info("Saving '" + guru + "'");
		Guru persisted = guruRepository().save(guru);
		log().info("Saved '" + persisted + "'");
	}

}
