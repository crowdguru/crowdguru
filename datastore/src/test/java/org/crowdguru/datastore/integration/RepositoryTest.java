package org.crowdguru.datastore.integration;

import java.sql.SQLException;
import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.datastore.helpers.DatabaseTesterHelper;
import org.crowdguru.datastore.helpers.FileOperationsHelper;
import org.crowdguru.datastore.helpers.SectorHelper;
import org.crowdguru.datastore.helpers.SkillHelper;
import org.crowdguru.datastore.helpers.UserHelper;
import org.crowdguru.datastore.repositories.SectorRepository;
import org.crowdguru.datastore.repositories.SkillRepository;
import org.crowdguru.datastore.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration({ "/datastoreContext.xml", "/datastoreTestContext.xml" })
@ActiveProfiles({"prod", "integrationtest"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryTest {

	@Autowired
	protected FileOperationsHelper fileHelper;

	@Autowired
	protected DatabaseTesterHelper databaseTester;
	
	@Autowired
	protected UserRepository userRepo;
	
	@Autowired
	protected SkillRepository skillRepo;
	
	@Autowired
	protected SectorRepository sectorRepo;
	
	@Autowired
	UserHelper userHelper;
	
	@Autowired
	SkillHelper skillHelper;
	
	@Autowired
	SectorHelper sectorHelper;

	@Before
	public void setUp() throws SQLException, Exception{
		//Clean first
		databaseTester.clean();
	}
	
	@Test
	public void testRepositories() {
		
		//Save user1
		User user1 = userHelper.user1();
		user1 = userRepo.save(user1);
		
		//Save user2
		User user2 = userHelper.user2();
		user2 = userRepo.save(user2);
		
		//Save skill1
		Skill skill1 = skillHelper.skill1();
		skill1 = skillRepo.save(skill1);
		
		//Save skill2
		Skill skill2 = skillHelper.skill2();
		skill2 = skillRepo.save(skill2);
		
		//Save sector1
		Sector sector1 = sectorHelper.sector1();
		sector1 = sectorRepo.save(sector1);
				
		//Save sector2
		Sector sector2 = sectorHelper.sector2();
		sector2 = sectorRepo.save(sector2);
	}
	
	@After
	public void check() throws SQLException, Exception{
/*		IDataSet current = databaseTester.getDataSet();
		IDataSet expected = fileHelper.loadFromFlatXmlFile("RepositoryTestExpected.xml");
		
		for(String tableName: current.getTableNames()){
			assertEquals(expected.getTable(tableName), current.getTable(tableName));
		}*/
		
		databaseTester.clean();
	}
}
