package org.crowdguru.datastore.context;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"prod"})
public class ProdDatastoreContextTest extends BaseDatastoreContextTest {
	
	private String dataSourcePropsPath = "META-INF/prod/datasource.properties";
	
	private String hibernatePropsPath = "META-INF/prod/hibernate.properties";
	
	@Before
	public void setUp() throws FileNotFoundException, IOException{
		sourceProps = new Properties();
		sourceProps.load(this.getClass()
				.getClassLoader()
				.getResourceAsStream(dataSourcePropsPath));
		
		hibernateProps = new Properties();
		hibernateProps.load(this.getClass()
				.getClassLoader()
				.getResourceAsStream(hibernatePropsPath));
	}
}
