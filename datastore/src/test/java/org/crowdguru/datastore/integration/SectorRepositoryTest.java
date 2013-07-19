package org.crowdguru.datastore.integration;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.dbunit.Assertion.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crowdguru.datastore.domain.Sector;
import org.crowdguru.datastore.helpers.SectorHelper;
import org.crowdguru.datastore.repositories.SectorRepository;
import org.crowdguru.datastore.validators.SectorValidator;
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
public class SectorRepositoryTest extends RepositoryTestCommon{

	@Autowired
	private SectorRepository cut;

	@Autowired
	private SectorHelper sectorHelper;

	@Autowired
	private SectorValidator sectorValidator;

	@Before
	public void setUp() throws DatabaseUnitException, SQLException, Exception {
		check = null;
		initialData = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/SectorRepositoryTest.xml");
		databaseTester.cleanInsert(initialData);
	}

	@Test
	public void findsSector() throws SQLException, Exception {
		Sector sector = cut.findOne(new Long(1));
		sectorValidator.validateSector1(sector);
	}

	@Test
	public void findsAllSectors() throws SQLException, Exception {
		List<Sector> sectors = cut.findAll();

		sectorValidator.validateSector1(sectors.get(0));
		sectorValidator.validateSector2(sectors.get(1));
		assertThat(sectors.size(), is(equalTo(2)));
	}

	@Test
	public void findsAllSectorsSorted() throws SQLException, Exception {
		List<Sector> sectors = cut.findAll(new Sort("name"));

		sectorValidator.validateSector1(sectors.get(0));
		sectorValidator.validateSector2(sectors.get(1));
		assertThat(sectors.size(), is(equalTo(2)));

		sectors = cut.findAll(new Sort(Direction.DESC, "id"));

		sectorValidator.validateSector1(sectors.get(1));
		sectorValidator.validateSector2(sectors.get(0));
		assertThat(sectors.size(), is(equalTo(2)));
	}

	@Test
	public void findsAllSectorsPaged() throws SQLException, Exception {
		Page<Sector> sectors = cut.findAll(new PageRequest(2, 1));

		assertThat(sectors.getTotalPages(), is(equalTo(2)));
		assertThat(sectors.getTotalElements(), is(equalTo((long) 2)));
	}

	@Test
	public void findsAllSectorsIterable() throws SQLException, Exception {
		ArrayList<Long> ids = new ArrayList<Long>();
		ids.add(new Long(1));
		Iterable<Sector> sectors = cut.findAll((Iterable<Long>) ids);

		Iterator<Sector> it = sectors.iterator();
		sectorValidator.validateSector1(it.next());
		assertThat(it.hasNext(), is(false));
	}

	@Test
	public void deletesSectorById() throws SQLException, Exception {
		cut.delete(new Long(2));

		ITable current = databaseTester.getDataSet().getTable("sector");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/deletesSectorExpected.xml");
		assertEquals(expected.getTable("sector"), current);
	}

	@Test
	public void deletesSectorByEntity() throws SQLException, Exception {
		cut.delete(cut.findOne(new Long(2)));

		ITable current = databaseTester.getDataSet().getTable("sector");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/deletesSectorExpected.xml");
		assertEquals(expected.getTable("sector"), current);
	}

	@Test
	public void deletesSectorByIterable() throws SQLException, Exception {
		ArrayList<Sector> sectors = new ArrayList<Sector>();
		sectors.add(cut.findOne(new Long(2)));
		cut.delete((Iterable<Sector>) sectors);

		ITable current = databaseTester.getDataSet().getTable("sector");
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/deletesSectorExpected.xml");
		assertEquals(expected.getTable("sector"), current);
	}

	@Test
	public void deletesAllSectorsInBatch() throws SQLException, Exception {
		// There shouldn't be any relation with other tables in order to execute
		// deleteAllInBatch()
		IDataSet initial = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/deletesAllSectorsInBatchInitial.xml");
		databaseTester.cleanInsert(initial);
		cut.deleteAllInBatch();

		ITable current = databaseTester.getDataSet().getTable("sector");
		assertThat(current.getRowCount(), is(equalTo(0)));
	}

	@Test
	public void deletesSectorsInBatch() throws SQLException, Exception {
		// There shouldn't be any relation with other tables in order to execute
		// deleteAllInBatch()
		IDataSet initial = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/deletesAllSectorsInBatchInitial.xml");
		databaseTester.cleanInsert(initial);

		ArrayList<Sector> sectors = new ArrayList<Sector>();
		sectors.add(cut.findOne(new Long(1)));
		sectors.add(cut.findOne(new Long(2)));
		cut.deleteInBatch((Iterable<Sector>) sectors);

		ITable current = databaseTester.getDataSet().getTable("sector");
		assertThat(current.getRowCount(), is(equalTo(0)));
	}

	@Test
	public void countsSectors() throws SQLException, Exception {
		long expectedCount = initialData.getTable("sector").getRowCount();
		assertThat(cut.count(), is(equalTo(expectedCount)));
	}

	@Test
	public void checksIfExists() throws SQLException, Exception {
		assertThat(cut.exists(new Long(1)), is(true));
		assertThat(cut.exists(new Long(3)), is(false));
	}

	@Test
	public void savesNewSector() throws SQLException, Exception {
		Sector sector = sectorHelper.sector3();
		assertThat(sector.getId(), is(nullValue()));
		cut.save(sector);
		assertThat(sector.getId(), is(notNullValue()));

		IDataSet current = databaseTester.getDataSet();
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/savesNewSectorExpected.xml");

		// Ids are subject to change after save/delete operation. Ignoring for
		// robustness.
		assertEqualsIgnoreCols(expected, current, "sector", new String[] { "id" });
	}

	@Test
	@Transactional
	public void savesAndFlushesNewSector() throws SQLException, Exception {
		Sector sector = sectorHelper.sector3();
		assertThat(sector.getId(), is(nullValue()));
		cut.saveAndFlush(sector);
		assertThat(sector.getId(), is(notNullValue()));

		check = new Runnable() {

			@Override
			public void run() {
				IDataSet current;
				try {
					current = databaseTester.getDataSet();
					IDataSet expected = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/savesNewSectorExpected.xml");
					assertEqualsIgnoreCols(expected, current, "sector", new String[] { "id" });
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	}

	@Test
	public void savesSectorsIterable() throws SQLException, Exception {
		cut.deleteAll();
		ArrayList<Sector> sectors = new ArrayList<Sector>();
		sectors.add(sectorHelper.sector1());
		sectors.add(sectorHelper.sector2());
		sectors.add(sectorHelper.sector3());
		cut.save(sectors);

		IDataSet current = databaseTester.getDataSet();
		IDataSet expected = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/savesNewSectorExpected.xml");
		assertEqualsIgnoreCols(expected, current, "sector", new String[] { "id" });
	}

	@Test
	@Transactional
	public void flushes() throws SQLException, Exception {
		cut.deleteAll();
		cut.save(sectorHelper.sector1());
		cut.save(sectorHelper.sector2());
		cut.save(sectorHelper.sector3());
		cut.flush();

		check = new Runnable() {

			@Override
			public void run() {
				IDataSet current;
				try {
					current = databaseTester.getDataSet();
					IDataSet expected = fileHelper.loadFromFlatXmlFile("SectorRepositoryTest/savesNewSectorExpected.xml");
					assertEqualsIgnoreCols(expected, current, "sector", new String[] { "id" });
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	}
}
