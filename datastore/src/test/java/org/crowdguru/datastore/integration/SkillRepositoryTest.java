package org.crowdguru.datastore.integration;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.helpers.SkillHelper;
import org.crowdguru.datastore.repositories.SkillRepository;
import org.crowdguru.datastore.validators.SkillValidator;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

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
		initialData = fileHelper.loadFromFlatXmlFile("SkillRepositoryTest.xml");
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
		assertThat(cut.count(), is(equalTo((long)1)));
	}

	@Test
	public void deletesSkillByEntity() throws SQLException, Exception {
		cut.delete(cut.findOne(new Long(2)));
		assertThat(cut.count(), is(equalTo((long)1)));
	}

	@Test
	public void deletesSkillByIterable() throws SQLException, Exception {
		ArrayList<Skill> skills = new ArrayList<Skill>();
		skills.add(cut.findOne(new Long(2)));
		cut.delete((Iterable<Skill>) skills);
		assertThat(cut.count(), is(equalTo((long)1)));
	}

	@Test
	public void deletesAllSkillsInBatch() throws SQLException, Exception {
		cut.deleteAllInBatch();
		assertThat(cut.count(), is(equalTo((long)0)));
	}

	@Test
	public void deletesSkillsInBatch() throws SQLException, Exception {

		ArrayList<Skill> skills = new ArrayList<Skill>();
		skills.add(cut.findOne(new Long(1)));
		skills.add(cut.findOne(new Long(2)));
		cut.deleteInBatch((Iterable<Skill>) skills);
		assertThat(cut.count(), is(equalTo((long)0)));
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
		long count = cut.count();
		cut.save(skill);
		assertThat(cut.count(), is(equalTo((long)(count+1))));
	}

	public void savesAndFlushesNewSkill() throws SQLException, Exception {
		Skill skill = skillHelper.skill3();
		long count = cut.count();
		cut.saveAndFlush(skill);
		assertThat(cut.count(), is(equalTo((long)(count+1))));
	}

	@Test
	public void savesSkillsIterable() throws SQLException, Exception {
		databaseTester.clean();
		ArrayList<Skill> skills = new ArrayList<Skill>();
		skills.add(skillHelper.skill1());
		skills.add(skillHelper.skill2());
		skills.add(skillHelper.skill3());
		cut.save(skills);
		assertThat(cut.count(), is(equalTo((long)3)));
	}

	@Test
	public void flushes() throws SQLException, Exception {
		databaseTester.clean();
		cut.save(skillHelper.skill1());
		cut.save(skillHelper.skill2());
		cut.save(skillHelper.skill3());
		cut.flush();
		assertThat(cut.count(), is(equalTo((long)3)));
	}
}
