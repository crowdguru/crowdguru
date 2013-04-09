package org.crowdguru.datastore.smoketest;

import static org.crowdguru.datastore.context.Singletons.guruRepository;

import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.repositories.UserRepository;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new App().run();
	}
	
	private void run() {
		UserRepository guruRepository = guruRepository();
		log().info("Got guru repository '" + guruRepository + "'");
		User guru = new User("GURU_FORENAME", "GURU_SURNAME");
		log().info("Saving '" + guru + "'");
		User persisted = guruRepository().save(guru);
		log().info("Saved '" + persisted + "'");
	}

}
