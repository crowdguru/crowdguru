package org.crowdguru.acceptance.usecase;

import org.crowdguru.acceptance.page.IndexPage;
import org.crowdguru.acceptance.page.LoginPage;
import org.crowdguru.acceptance.page.ProfilePage;
import org.crowdguru.acceptance.page.RegisterPage;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.UserHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * https://github.com/crowdguru/crowdguru/wiki/UC1-Create-account
 */
public class UC1CreateAccount extends UseCaseTest{

	UserHelper userHelper;
	
	@Autowired
	public void setUserHelper(UserHelper userHelper){
		this.userHelper = userHelper;
	}
	
	@Before
	public void setup() throws Exception {
		databaseTester.clean();
	}
	
	@After
	public void logout(){
        frontEnd.logOut();
	}
	
	@Test
	public void createsAccountAndLogsOn() throws Exception {
		User user = userHelper.user1();
		
        IndexPage indexPage = frontEnd.goHome();
        
        LoginPage loginPage = indexPage.clickLogOnButton();
        
        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.register(user.getForename(), user.getSurname(), 
        		user.getEmail(), user.getPassword(), user.getType());
        
        registerPage.assertSubmissionNotificationPresent();
        loginPage = registerPage.closeSubmissionNotification();
        
        ProfilePage profilePage = loginPage.login(user.getEmail(), user.getPassword());
        
        profilePage.assertProfileField(user.getForename());
        profilePage.assertProfileField(user.getSurname());
        profilePage.assertProfileField(user.getEmail());
        profilePage.assertProfileField(user.getType().toString());
	}
}
