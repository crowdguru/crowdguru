package org.crowdguru.datastore.tests;

import org.crowdguru.datastore.repositories.GuruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

public class BaseTest {
	
	public void clearDown() {
		guruRepository.deleteAll();
		assertEquals(0, guruRepository.count());
		log().info("Cleared out temporary repository, no. of gurus: '" + guruRepository.count() + "'");
	}
	
	@Autowired
	private GuruRepository guruRepository;

}
