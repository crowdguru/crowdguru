package org.crowdguru.datastore.integration;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.dbunit.Assertion.*;
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
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
public class UserRepositoryTest extends RepositoryTestCommon{

	@Autowired
	private UserRepository cut;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private UserValidator userValidator;

	@Before
	public void setUp() throws DatabaseUnitException, SQLException, Exception {
		check = null;
		initialData = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/UserRepositoryTest.xml");
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
		List<User> users = cut.findAll(new Sort("homeTown"));

		userValidator.validateUser1(users.get(0));
		userValidator.validateUser2(users.get(1));
		assertThat(users.size(), is(equalTo(2)));

		users = cut.findAll(new Sort(Direction.DESC, "forename"));

		userValidator.validateUser1(users.get(1));
		userValidator.validateUser2(users.get(0));
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

		ITable current = databaseTester.getDataSet().getTable("user");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/deletesUserExpected.xml");
		assertEquals(expected.getTable("user"), current);
	}

	@Test
	public void deletesUserByEntity() throws SQLException, Exception {
		cut.delete(cut.findOne(new Long(2)));

		ITable current = databaseTester.getDataSet().getTable("user");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/deletesUserExpected.xml");
		assertEquals(expected.getTable("user"), current);
	}

	@Test
	public void deletesUserByIterable() throws SQLException, Exception {
		ArrayList<User> users = new ArrayList<User>();
		users.add(cut.findOne(new Long(2)));
		cut.delete((Iterable<User>) users);

		ITable current = databaseTester.getDataSet().getTable("user");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/deletesUserExpected.xml");
		assertEquals(expected.getTable("user"), current);
	}

	@Test
	public void deletesAllUsersInBatch() throws SQLException, Exception {
		// There shouldn't be any relation with other tables in order to execute
		// deleteAllInBatch()
		IDataSet initial = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/deletesAllUsersInBatchInitial.xml");
		databaseTester.cleanInsert(initial);
		cut.deleteAllInBatch();

		ITable current = databaseTester.getDataSet().getTable("user");
		assertThat(current.getRowCount(), is(equalTo(0)));
	}

	@Test
	public void deletesUsersInBatch() throws SQLException, Exception {
		// There shouldn't be any relation with other tables in order to execute
		// deleteAllInBatch()
		IDataSet initial = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/deletesAllUsersInBatchInitial.xml");
		databaseTester.cleanInsert(initial);

		ArrayList<User> users = new ArrayList<User>();
		users.add(cut.findOne(new Long(1)));
		users.add(cut.findOne(new Long(2)));
		cut.deleteInBatch((Iterable<User>) users);

		ITable current = databaseTester.getDataSet().getTable("user");
		assertThat(current.getRowCount(), is(equalTo(0)));
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
		User user = userHelper.user3();
		assertThat(user.getId(), is(nullValue()));
		cut.save(user);
		assertThat(user.getId(), is(notNullValue()));

		IDataSet current = databaseTester.getDataSet();
		IDataSet expected = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/savesNewUserExpected.xml");

		// Ids are subject to change after save/delete operation. Ignoring for
		// robustness.
		assertEqualsIgnoreCols(expected, current, "user", new String[] { "id" });
	}

	@Test
	@Transactional
	public void savesAndFlushesNewUser() throws SQLException, Exception {
		User user = userHelper.user3();
		assertThat(user.getId(), is(nullValue()));
		cut.saveAndFlush(user);
		assertThat(user.getId(), is(notNullValue()));

		check = new Runnable() {

			@Override
			public void run() {
				IDataSet current;
				try {
					current = databaseTester.getDataSet();
					IDataSet expected = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/savesNewUserExpected.xml");
					assertEqualsIgnoreCols(expected, current, "user", new String[] { "id" });
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	}

	@Test
	public void savesUsersIterable() throws SQLException, Exception {
		cut.deleteAll();
		ArrayList<User> users = new ArrayList<User>();
		users.add(userHelper.user1());
		users.add(userHelper.user2());
		users.add(userHelper.user3());
		cut.save(users);

		IDataSet current = databaseTester.getDataSet();
		IDataSet expected = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/savesNewUserExpected.xml");
		assertEqualsIgnoreCols(expected, current, "user", new String[] { "id" });
	}

	@Test
	@Transactional
	public void flushes() throws SQLException, Exception {
		cut.deleteAll();
		cut.save(userHelper.user1());
		cut.save(userHelper.user2());
		cut.save(userHelper.user3());
		cut.flush();

		check = new Runnable() {

			@Override
			public void run() {
				IDataSet current;
				try {
					current = databaseTester.getDataSet();
					IDataSet expected = fileHelper.loadFromFlatXmlFile("UserRepositoryTest/savesNewUserExpected.xml");
					assertEqualsIgnoreCols(expected, current, "user", new String[] { "id" });
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	}
}
