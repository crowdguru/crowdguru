package org.crowdguru.datastore.domain;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.crowdguru.datastore.constants.SkillConstants;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.SkillHelper;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.helpers.impl.SkillHelperImpl;
import org.crowdguru.datastore.helpers.impl.UserHelperImpl;

import junit.framework.TestCase;

public class SkillTest extends TestCase {
	Skill cut;
	
	SkillHelper skillHelper;
	
	public void setUp(){
		cut = new Skill();
		skillHelper = new SkillHelperImpl();
	}
	
	public void testId(){
		assertThat(cut.getId(), is(nullValue()));
		Long id = new Long(13234);
		cut.setId(id);
		
		assertThat(cut.getId(), is(equalTo(id)));
	}
	
	public void testName(){
		assertThat(cut.getName(), is(nullValue()));
		
		cut.setName(SkillConstants.SKILL_1_NAME);
		
		assertThat(cut.getName(), is(equalTo(SkillConstants.SKILL_1_NAME)));
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
		cut = skillHelper.skill1();
		Long id = new Long(1424234);
		cut.setId(id);
		assertThat(cut.toString(), hasToString(stringContainsInOrder(
				Arrays.asList(id.toString(),
						SkillConstants.SKILL_1_NAME
						))));
	}
}
