package org.crowdguru.acceptance.usecase;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.crowdguru.acceptance.page.CreateTaskPage;
import org.crowdguru.acceptance.page.IndexPage;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * https://github.com/crowdguru/crowdguru/wiki/UC3-Create-task
 */
public class UC3CreateTask extends UseCaseTest{
	
	
	User keyContact;
	
	Cause cause;
	
	@Before
	public void setup() throws Exception {
		keyContact = userRepository.save(userHelper.user1());
		cause = causeRepository.save(causeHelper.cause1());
		
		Set<Cause> causes = new HashSet<Cause>();
		causes.add(cause);
		keyContact.setCauses(causes);
		userRepository.save(keyContact);
	}
	
	@Test
	public void createsAccountAndLogsOn() throws Exception {

		Task task = taskHelper.task1();
		IndexPage indexPage = frontEnd.goToIndexPage();
        indexPage.login(keyContact.getEmail(), keyContact.getPassword());
        
        CreateTaskPage createTaskPage = indexPage.clickCreateTaskLink();
        createTaskPage.inputTitle(task.getTitle());
        createTaskPage.inputShortDescription(task.getShortDescription());
        createTaskPage.inputLongDescription(task.getLongDescription());
        createTaskPage.clickSubmitButton();
	}
	
	@After
	public void logOut(){
		frontEnd.logOut();
	}
}
