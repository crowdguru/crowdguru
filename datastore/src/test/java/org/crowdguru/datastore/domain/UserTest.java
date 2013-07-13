package org.crowdguru.datastore.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.SectorHelper;
import org.crowdguru.datastore.helpers.SkillHelper;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.helpers.impl.SectorHelperImpl;
import org.crowdguru.datastore.helpers.impl.SkillHelperImpl;
import org.crowdguru.datastore.helpers.impl.UserHelperImpl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.crowdguru.datastore.constants.UserConstants;

public class UserTest extends TestCase{

	User cut;
	
	UserHelper userHelper;
	
	public void setUp(){
		cut = new User();
		userHelper = new UserHelperImpl();
	}
	
	public void testId(){
		assertThat(cut.getId(), is(nullValue()));
		Long id = new Long(1123112321);
		cut.setId(id);
		
		assertThat(cut.getId(), is(equalTo(id)));
	}
	
	public void testForename(){
		assertThat(cut.getForename(), is(nullValue()));
		
		cut.setForename(UserConstants.USER_1_FORENAME);
		
		assertThat(cut.getForename(), is(equalTo(UserConstants.USER_1_FORENAME)));
	}
	
	public void testSurname(){
		assertThat(cut.getSurname(), is(nullValue()));
		
		cut.setSurname(UserConstants.USER_1_SURNAME);
		
		assertThat(cut.getSurname(), is(equalTo(UserConstants.USER_1_SURNAME)));
	}
	
	public void testAboutMe(){
		assertThat(cut.getAboutMe(), is(nullValue()));
		
		cut.setAboutMe(UserConstants.USER_1_ABOUTME);
		
		assertThat(cut.getAboutMe(), is(equalTo(UserConstants.USER_1_ABOUTME)));
	}
	
	public void testHometown(){
		assertThat(cut.getHomeTown(), is(nullValue()));
		
		cut.setHomeTown(UserConstants.USER_1_HOMETOWN);
		
		assertThat(cut.getHomeTown(), is(equalTo(UserConstants.USER_1_HOMETOWN)));
	}
	
	public void testProviderId(){
		assertThat(cut.getProviderId(), is(nullValue()));
		
		cut.setProviderId(UserConstants.USER_1_PROVIDER_ID);
		
		assertThat(cut.getProviderId(), is(equalTo(UserConstants.USER_1_PROVIDER_ID)));
	}
	
	public void testProviderUserId(){
		assertThat(cut.getProviderUserId(), is(nullValue()));
		
		cut.setProviderUserId(UserConstants.USER_1_PROVIDER_USERID);
		
		assertThat(cut.getProviderUserId(), is(equalTo(UserConstants.USER_1_PROVIDER_USERID)));
	}
	
	public void testSkills(){
		Set<Skill> skills = cut.getSkills();
		assertThat(skills, is(nullValue()));

		Set<Skill> newSkills = new HashSet<Skill>();
		SkillHelper skillHelper = new SkillHelperImpl();
		Skill aSkill = skillHelper.skill1();
		newSkills.add(aSkill);
		cut.setSkills(newSkills);
		
		assertThat(cut.getSkills(), contains(aSkill));
		assertThat(cut.getSkills().size(), is(equalTo(1)));
	}
	
	public void testSectors(){
		Set<Sector> sectors = cut.getSectors();
		assertThat(sectors, is(nullValue()));

		Set<Sector> newSectors = new HashSet<Sector>();
		SectorHelper sectorHelper = new SectorHelperImpl();
		Sector aSector = sectorHelper.sector1();
		newSectors.add(aSector);
		cut.setSectors(newSectors);
		
		assertThat(cut.getSectors(), contains(aSector));
		assertThat(cut.getSectors().size(), is(equalTo(1)));
	}
	
	public void testToString(){
		cut = userHelper.user1();
		Long id = new Long(11231);
		cut.setId(id);
		
		assertThat(cut.toString(), hasToString(stringContainsInOrder(
				Arrays.asList(id.toString(),
						UserConstants.USER_1_FORENAME,
						UserConstants.USER_1_SURNAME,
						UserConstants.USER_1_PROVIDER_ID,
						UserConstants.USER_1_PROVIDER_USERID
						))));
	}
	
}
