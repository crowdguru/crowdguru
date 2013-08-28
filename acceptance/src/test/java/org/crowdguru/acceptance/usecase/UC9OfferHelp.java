package org.crowdguru.acceptance.usecase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.crowdguru.acceptance.page.BrowseTasksPage;
import org.crowdguru.acceptance.page.CreateTaskPage;
import org.crowdguru.acceptance.page.HomePage;
import org.crowdguru.acceptance.page.IndexPage;
import org.crowdguru.acceptance.page.TaskPage;
import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.CauseHelper;
import org.crowdguru.datastore.helpers.TaskHelper;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.repositories.CauseRepository;
import org.crowdguru.datastore.repositories.TaskRepository;
import org.crowdguru.datastore.repositories.UserRepository;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * https://github.com/crowdguru/crowdguru/wiki/UC9-Offer-help
 */
public class UC9OfferHelp extends UseCaseTest{
	
	UserHelper userHelper;
	
	CauseHelper causeHelper;
	
	TaskHelper taskHelper;
	
	User keyContact;
	
	User guru;
	
	Cause cause;
	
	Task task;
	
	@Autowired
	public void setUserHelper(UserHelper userHelper){
		this.userHelper = userHelper;
	}
	
	@Autowired
	public void setTaskHelper(TaskHelper taskHelper){
		this.taskHelper = taskHelper;
	}
	
	@Autowired
	public void setCauseHelper(CauseHelper causeHelper){
		this.causeHelper = causeHelper;
	}
	
	@Before
	@Transactional
	public void setup() throws Exception {
		keyContact = userRepository.save(userHelper.user1());
		guru = userRepository.save(userHelper.user2());
		cause = causeRepository.save(causeHelper.cause1());
		task = taskRepository.save(taskHelper.task1());
		
		Set<Cause> causes = new HashSet<Cause>();
		causes.add(cause);
		keyContact.setCauses(causes);
		userRepository.save(keyContact);
		
		
		task.setOwner(keyContact);
		task.setCause(cause);
		taskRepository.save(task);
	}
	
	@Test
	public void offersHelp() throws Exception {
		User guru = userHelper.user2();
		
		IndexPage indexPage = frontEnd.goToIndexPage();
        indexPage.login(guru.getEmail(), guru.getPassword());
        TaskPage taskPage = frontEnd.goToTaskPage(task.getId());
        taskPage.clickOfferHelpButton();
        taskPage.assertOfferHelpModalPresent();
        taskPage.clickSendOfferButton();
        frontEnd.logOut();
        indexPage = frontEnd.goToIndexPage();
        indexPage.login(keyContact.getEmail(), keyContact.getPassword());
	}
	
	@After
	public void logOut() throws DataSetException, FileNotFoundException, IOException, SQLException, Exception{	
		frontEnd.logOut();
	}
}
