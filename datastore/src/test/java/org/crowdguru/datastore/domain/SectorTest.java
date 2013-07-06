package org.crowdguru.datastore.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.crowdguru.datastore.constants.SectorConstants;
import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.SectorHelper;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.helpers.impl.SectorHelperImpl;
import org.crowdguru.datastore.helpers.impl.UserHelperImpl;

import junit.framework.TestCase;

public class SectorTest extends TestCase {

	Sector cut;
	
	SectorHelper sectorHelper;
	
	public void setUp(){
		cut = new Sector();
		sectorHelper = new SectorHelperImpl();
	}
	
	public void testId(){
		assertThat(cut.getId(), is(nullValue()));
		Long id = new Long(112334);
		cut.setId(id);
		
		assertThat(cut.getId(), is(equalTo(id)));
	}
	
	public void testName(){
		assertThat(cut.getName(), is(nullValue()));
		
		cut.setName(SectorConstants.SECTOR_1_NAME);
		
		assertThat(cut.getName(), is(equalTo(SectorConstants.SECTOR_1_NAME)));
	}
	
	public void testUsers(){
		assertThat(cut.getUsers(), is(nullValue()));
		
		Set<User> users = new HashSet<User>();
		UserHelper userHelper = new UserHelperImpl();
		User aUser = userHelper.user1();
		users.add(aUser);
		cut.setUsers(users);
		
		assertThat(cut.getUsers(), contains(aUser));
		assertThat(cut.getUsers().size(), is(equalTo(1)));
	}
	
	public void testToString(){
		cut = sectorHelper.sector1();
		Long id = new Long(47284379);
		cut.setId(id);
		
		assertThat(cut.toString(), hasToString(stringContainsInOrder(
				Arrays.asList(id.toString(),
						SectorConstants.SECTOR_1_NAME
						))));
	}
}
