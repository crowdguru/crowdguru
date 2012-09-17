package org.crowdguru.datastore.tests;

import org.crowdguru.datastore.domain.Guru;
import org.crowdguru.datastore.helpers.GuruHelper;
import org.crowdguru.datastore.validators.GuruValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/datastoreContext.xml" })
public class GuruTests extends BaseTest {
	
	@Test
	public void testGuru1() {
		Guru guru = guruHelper.guru1();
		guruValidator.validateGuru1(guru);
		log().info("Got unattached guru '" + guru + "'");
		
		Guru merged = getGuruRepository().save(guru);
		guruValidator.validateGuru1(merged);
		log().info("Got merged guru '" + merged + "'");
		
		Guru retrieved = getGuruRepository().findOne(merged.getNodeId());
		guruValidator.validateGuru1(retrieved);
		log().info("Got retrieved guru '" + retrieved + "'");
	}
	
	@Autowired
	private GuruHelper guruHelper;
	
	@Autowired
	private GuruValidator guruValidator;
}
