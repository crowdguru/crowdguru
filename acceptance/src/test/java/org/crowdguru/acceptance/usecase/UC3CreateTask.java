package org.crowdguru.acceptance.usecase;

import org.crowdguru.acceptance.page.CreateTaskPage;
import org.crowdguru.acceptance.page.HomePage;
import org.crowdguru.acceptance.page.IndexPage;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.UserHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * https://github.com/crowdguru/crowdguru/wiki/UC3-Create-task
 */
public class UC3CreateTask extends UseCaseTest{
	
	UserHelper userHelper;
	
	@Autowired
	public void setUserHelper(UserHelper userHelper){
		this.userHelper = userHelper;
	}
	
	@Before
	public void setup() throws Exception {
		loadDataSet("UC3.xml");
	}
	
	@Test
	public void createsAccountAndLogsOn() throws Exception {

		User user = userHelper.user1();
		
		IndexPage indexPage = frontEnd.goHome();
        HomePage profilePage = indexPage.login(user.getEmail(), user.getPassword());
        
        CreateTaskPage createTaskPage = profilePage.clickCreateTaskLink();
        createTaskPage.inputTitle("Test task");
        createTaskPage.inputShortDescription("Test short description");
        createTaskPage.inputLongDescription("Test long description");
        createTaskPage.selectAmount(3);
        createTaskPage.selectUnit("weeks");
        createTaskPage.clickSubmitButton();
	}
	
	@After
	public void logOut(){
		frontEnd.logOut();
	}
}
