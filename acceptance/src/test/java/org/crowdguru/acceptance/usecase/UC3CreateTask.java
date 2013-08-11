package org.crowdguru.acceptance.usecase;

import org.crowdguru.acceptance.page.IndexPage;
import org.crowdguru.acceptance.page.LoginPage;
import org.crowdguru.acceptance.page.ProfilePage;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.UserHelper;
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
        LoginPage loginPage = indexPage.clickLogOnButton();
        
        ProfilePage profilePage = loginPage.login(user.getEmail(), user.getPassword());
        profilePage.clickCreateTaskLink();
        
        frontEnd.logOut();
	}
	
}
