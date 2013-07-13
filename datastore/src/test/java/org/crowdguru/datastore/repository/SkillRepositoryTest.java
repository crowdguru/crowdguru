package org.crowdguru.datastore.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.dbunit.Assertion.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.helpers.SkillHelper;
import org.crowdguru.datastore.repositories.SkillRepository;
import org.crowdguru.datastore.validators.SkillValidator;
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
public class SkillRepositoryTest extends BaseRepositoryTest{

	@Autowired
	private SkillRepository cut;

	@Autowired
	private SkillHelper skillHelper;

	@Autowired
	private SkillValidator skillValidator;

	@Before
	public void setUp() throws DatabaseUnitException, SQLException, Exception {
		check = null;
		initialData = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/SkillRepositoryTest.xml");
		databaseTester.cleanInsert(initialData);
	}

	@Test
	public void findsSkill() throws SQLException, Exception {
		Skill skill = cut.findOne(new Long(1));
		skillValidator.validateSkill1(skill);
	}

	@Test
	public void findsAllSkills() throws SQLException, Exception {
		List<Skill> skills = cut.findAll();

		skillValidator.validateSkill1(skills.get(0));
		skillValidator.validateSkill2(skills.get(1));
		assertThat(skills.size(), is(equalTo(2)));
	}

	@Test
	public void findsAllSkillsSorted() throws SQLException, Exception {
		List<Skill> skills = cut.findAll(new Sort("name"));

		skillValidator.validateSkill1(skills.get(0));
		skillValidator.validateSkill2(skills.get(1));
		assertThat(skills.size(), is(equalTo(2)));

		skills = cut.findAll(new Sort(Direction.DESC, "id"));

		skillValidator.validateSkill1(skills.get(1));
		skillValidator.validateSkill2(skills.get(0));
		assertThat(skills.size(), is(equalTo(2)));
	}

	@Test
	public void findsAllSkillsPaged() throws SQLException, Exception {
		Page<Skill> skills = cut.findAll(new PageRequest(2, 1));

		assertThat(skills.getTotalPages(), is(equalTo(2)));
		assertThat(skills.getTotalElements(), is(equalTo((long) 2)));
	}

	@Test
	public void findsAllSkillsIterable() throws SQLException, Exception {
		ArrayList<Long> ids = new ArrayList<Long>();
		ids.add(new Long(1));
		Iterable<Skill> skills = cut.findAll((Iterable<Long>) ids);

		Iterator<Skill> it = skills.iterator();
		skillValidator.validateSkill1(it.next());
		assertThat(it.hasNext(), is(false));
	}

	@Test
	public void deletesSkillById() throws SQLException, Exception {
		cut.delete(new Long(2));

		ITable current = databaseTester.getDataSet().getTable("skill");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/deletesSkillExpected.xml");
		assertEquals(expected.getTable("skill"), current);
	}

	@Test
	public void deletesSkillByEntity() throws SQLException, Exception {
		cut.delete(cut.findOne(new Long(2)));

		ITable current = databaseTester.getDataSet().getTable("skill");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/deletesSkillExpected.xml");
		assertEquals(expected.getTable("skill"), current);
	}

	@Test
	public void deletesSkillByIterable() throws SQLException, Exception {
		ArrayList<Skill> skills = new ArrayList<Skill>();
		skills.add(cut.findOne(new Long(2)));
		cut.delete((Iterable<Skill>) skills);

		ITable current = databaseTester.getDataSet().getTable("skill");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/deletesSkillExpected.xml");
		assertEquals(expected.getTable("skill"), current);
	}

	@Test
	public void deletesAllSkillsInBatch() throws SQLException, Exception {
		// There shouldn't be any relation with other tables in order to execute
		// deleteAllInBatch()
		IDataSet initial = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/deletesAllSkillsInBatchInitial.xml");
		databaseTester.cleanInsert(initial);
		cut.deleteAllInBatch();

		ITable current = databaseTester.getDataSet().getTable("skill");
		assertThat(current.getRowCount(), is(equalTo(0)));
	}

	@Test
	public void deletesSkillsInBatch() throws SQLException, Exception {
		// There shouldn't be any relation with other tables in order to execute
		// deleteAllInBatch()
		IDataSet initial = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/deletesAllSkillsInBatchInitial.xml");
		databaseTester.cleanInsert(initial);

		ArrayList<Skill> skills = new ArrayList<Skill>();
		skills.add(cut.findOne(new Long(1)));
		skills.add(cut.findOne(new Long(2)));
		cut.deleteInBatch((Iterable<Skill>) skills);

		ITable current = databaseTester.getDataSet().getTable("skill");
		assertThat(current.getRowCount(), is(equalTo(0)));
	}

	@Test
	public void countsSkills() throws SQLException, Exception {
		long expectedCount = initialData.getTable("skill").getRowCount();
		assertThat(cut.count(), is(equalTo(expectedCount)));
	}

	@Test
	public void checksIfExists() throws SQLException, Exception {
		assertThat(cut.exists(new Long(1)), is(true));
		assertThat(cut.exists(new Long(3)), is(false));
	}

	@Test
	public void savesNewSkill() throws SQLException, Exception {
		Skill skill = skillHelper.skill3();
		assertThat(skill.getId(), is(nullValue()));
		cut.save(skill);
		assertThat(skill.getId(), is(notNullValue()));

		IDataSet current = databaseTester.getDataSet();
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/savesNewSkillExpected.xml");

		// Ids are subject to change after save/delete operation. Ignoring for
		// robustness.
		assertEqualsIgnoreCols(expected, current, "skill", new String[] { "id" });
	}

	@Test
	@Transactional
	public void savesAndFlushesNewSkill() throws SQLException, Exception {
		Skill skill = skillHelper.skill3();
		assertThat(skill.getId(), is(nullValue()));
		cut.saveAndFlush(skill);
		assertThat(skill.getId(), is(notNullValue()));

		check = new Runnable() {

			@Override
			public void run() {
				IDataSet current;
				try {
					current = databaseTester.getDataSet();
					IDataSet expected = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/savesNewSkillExpected.xml");
					assertEqualsIgnoreCols(expected, current, "skill", new String[] { "id" });
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	}

	@Test
	public void savesSkillsIterable() throws SQLException, Exception {
		cut.deleteAll();
		ArrayList<Skill> skills = new ArrayList<Skill>();
		skills.add(skillHelper.skill1());
		skills.add(skillHelper.skill2());
		skills.add(skillHelper.skill3());
		cut.save(skills);

		IDataSet current = databaseTester.getDataSet();
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/savesNewSkillExpected.xml");
		assertEqualsIgnoreCols(expected, current, "skill", new String[] { "id" });
	}

	@Test
	@Transactional
	public void flushes() throws SQLException, Exception {
		cut.deleteAll();
		cut.save(skillHelper.skill1());
		cut.save(skillHelper.skill2());
		cut.save(skillHelper.skill3());
		cut.flush();

		check = new Runnable() {

			@Override
			public void run() {
				IDataSet current;
				try {
					current = databaseTester.getDataSet();
					IDataSet expected = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest/savesNewSkillExpected.xml");
					assertEqualsIgnoreCols(expected, current, "skill", new String[] { "id" });
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	}
}
