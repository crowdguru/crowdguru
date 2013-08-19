package org.crowdguru.acceptance.usecase;

import javax.sql.DataSource;

import org.crowdguru.acceptance.page.IndexPage;
import org.crowdguru.acceptance.page.ProfilePage;
import org.crowdguru.acceptance.page.RegisterPage;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.CauseHelper;
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
	
	CauseHelper causeHelper;
	
	@Autowired
	public void setUserHelper(UserHelper userHelper){
		this.userHelper = userHelper;
	}
	
	@Autowired
	public void setCauseHelper(CauseHelper causeHelper){
		this.causeHelper = causeHelper;
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
	public void signsUpAGuru(){
		User user = userHelper.user1();
		
		RegisterPage registerPage = goToRegistrationPage();
		fillInMandotaryFields(user, registerPage);
		registerPage.typeLocation(user.getLocation());
		registerPage.clickGuruCheckbox();
		registerPage.typeShortProfile(user.getShortProfile());
		registerPage.clickSubmitButton();
		registerPage.assertSubmissionNotificationPresent();
	}
	
	@Test
	public void signsUpAKeyContact(){
		User user = userHelper.user2();
		Cause cause = causeHelper.cause1();
		RegisterPage registerPage = goToRegistrationPage();
		fillInMandotaryFields(user, registerPage);
		registerPage.typeLocation(user.getLocation());
		registerPage.clickKeyContactCheckbox();
		registerPage.typeCauseName(cause.getName());
		registerPage.clickSubmitButton();
		registerPage.assertSubmissionNotificationPresent();
	}
	
	private void fillInMandotaryFields(User user, RegisterPage registrationPage){
		registrationPage.typeForename(user.getForename());
		registrationPage.typeSurname(user.getSurname());
		registrationPage.typeEmail(user.getEmail());
		registrationPage.typePassword(user.getPassword());
	}
	
	private RegisterPage goToRegistrationPage(){
		IndexPage indexPage = frontEnd.goHome();
		return indexPage.clickBecomeGuruButton();
	}
}
