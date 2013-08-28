package org.crowdguru.datastore.integration;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.crowdguru.datastore.constants.UserConstants;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.repositories.UserRepository;
import org.crowdguru.datastore.validators.UserValidator;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class UserRepositoryTest extends BaseRepositoryTest{

	@Autowired
	private UserRepository cut;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private UserValidator userValidator;

	@Before
	public void setUp() throws DatabaseUnitException, SQLException, Exception {
		initialData = fileHelper.loadFromFlatXmlFile("UserRepositoryTest.xml");
		databaseTester.cleanInsert(initialData);
	}

	@Test
	public void findsUser() throws SQLException, Exception {
		User user = cut.findOne(new Long(1));
		userValidator.validateUser1(user);
	}

	@Test
	public void find() throws SQLException, Exception {
		User user = cut.findByProviderIdAndProviderUserId(UserConstants.USER_1_PROVIDER_ID,
				UserConstants.USER_1_PROVIDER_USERID);

		userValidator.validateUser1(user);
	}

	@Test
	public void findsAllUsers() throws SQLException, Exception {
		List<User> users = cut.findAll();

		userValidator.validateUser1(users.get(0));
		userValidator.validateUser2(users.get(1));
		assertThat(users.size(), is(equalTo(2)));
	}

	@Test
	public void findsAllUsersSorted() throws SQLException, Exception {
		List<User> users = cut.findAll(new Sort("location"));
		assertThat(users.size(), is(equalTo(2)));
		users = cut.findAll(new Sort(Direction.DESC, "forename"));
		assertThat(users.size(), is(equalTo(2)));
	}

	@Test
	public void findsAllUsersPaged() throws SQLException, Exception {
		Page<User> users = cut.findAll(new PageRequest(2, 1));

		assertThat(users.getTotalPages(), is(equalTo(2)));
		assertThat(users.getTotalElements(), is(equalTo((long) 2)));
	}

	@Test
	public void findsAllUsersIterable() throws SQLException, Exception {
		ArrayList<Long> ids = new ArrayList<Long>();
		ids.add(new Long(1));
		Iterable<User> users = cut.findAll((Iterable<Long>) ids);

		Iterator<User> it = users.iterator();
		userValidator.validateUser1(it.next());
		assertThat(it.hasNext(), is(false));
	}

	@Test
	public void deletesUserById() throws SQLException, Exception {
		cut.delete(new Long(2));
		assertThat(cut.findOne(new Long(2)), is(nullValue()));
	}

	@Test
	public void deletesUserByEntity() throws SQLException, Exception {
		cut.delete(cut.findOne(new Long(2)));
		assertThat(cut.findOne(new Long(2)), is(nullValue()));
	}

	@Test
	public void deletesUserByIterable() throws SQLException, Exception {
		ArrayList<User> users = new ArrayList<User>();
		users.add(cut.findOne(new Long(2)));
		cut.delete((Iterable<User>) users);
		assertThat(cut.findOne(new Long(2)), is(nullValue()));
	}

	@Test
	public void deletesAllUsersInBatch() throws SQLException, Exception {
		cut.deleteAllInBatch();
		assertThat(cut.count(), is(equalTo((long)0)));
	}

	@Test
	public void deletesUsersInBatch() throws SQLException, Exception {
		List<User> users = cut.findAll();
		cut.deleteInBatch((Iterable<User>) users);
		assertThat(cut.count(), is(equalTo((long)0)));
	}

	@Test
	public void countsUsers() throws SQLException, Exception {
		long expectedCount = initialData.getTable("user").getRowCount();
		assertThat(cut.count(), is(equalTo(expectedCount)));
	}

	@Test
	public void checksIfExists() throws SQLException, Exception {
		assertThat(cut.exists(new Long(1)), is(true));
		assertThat(cut.exists(new Long(3)), is(false));
	}

	
	@Test
	public void savesNewUser() throws SQLException, Exception {
		long count = cut.count();
		User user = userHelper.user3();
		cut.save(user);
		assertThat(cut.count(), is(equalTo(count + 1)));
	}

	@Test
	public void savesAndFlushesNewUser() throws SQLException, Exception {
		long count = cut.count();
		User user = userHelper.user3();
		cut.saveAndFlush(user);
		assertThat(cut.count(), is(equalTo(count + 1)));
	}

	@Test
	public void savesUsersIterable() throws SQLException, Exception {
		databaseTester.clean();
		ArrayList<User> users = new ArrayList<User>();
		users.add(userHelper.user1());
		users.add(userHelper.user2());
		users.add(userHelper.user3());
		cut.save(users);
		assertThat(cut.count(), is(equalTo((long)3)));
	}

	@Test
	public void flushes() throws SQLException, Exception {
		databaseTester.clean();
		cut.save(userHelper.user1());
		cut.save(userHelper.user2());
		cut.save(userHelper.user3());
		cut.flush();
		assertThat(cut.count(), is(equalTo((long)3)));
	}
}
