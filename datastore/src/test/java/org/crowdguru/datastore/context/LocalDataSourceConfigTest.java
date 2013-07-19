package org.crowdguru.datastore.context;

import org.crowdguru.datastore.context.LocalDataSourceConfig;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringValueResolver;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class LocalDataSourceConfigTest{

	/**
	 * Profile for local development environment
	 * 
	 * <p>
	 * Creates and initializes H2 in memory database
	 * </P>
	 */
	private static final String DEV_PROFILE_NAME = "dev";
	
	/**
	 * Profile for local production environment
	 * 
	 * <p>
	 * Connects to MySQL server
	 * </p>
	 */
	private static final String PROD_PROFILE_NAME = "prod";
	
	/**
	 * The bean under test
	 */
	private static final String CONFIG_BEAN_NAME = "localDataSourceConfig";
	
	/**
	 * For resolving SpEL expressions while beans are being
	 * Initialised
	 */
	@Mock
	private StringValueResolver mockResolver;
	
	private AnnotationConfigApplicationContext context;
		
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		context = new AnnotationConfigApplicationContext();
		
		Mockito.when(mockResolver.resolveStringValue("${datastore.driver}"))
			.thenReturn("org.h2.Driver");
		Mockito.when(mockResolver.resolveStringValue("${datastore.user}"))
			.thenReturn("crowdguru");
		Mockito.when(mockResolver.resolveStringValue("${datastore.password}"))
			.thenReturn("");
		Mockito.when(mockResolver.resolveStringValue("${datastore.url}"))
			.thenReturn("jdbc:h2:mem:crowdguru;DB_CLOSE_DELAY=-1");
	}

	private void initializeWithProfile(String profile) {
		context.getEnvironment().setActiveProfiles(profile);
		context.register(LocalDataSourceConfig.class);
		context.getBeanFactory().addEmbeddedValueResolver(mockResolver);
		context.refresh();
	}
	
	@Test
	public void doesNotExistWithoutProfile(){
		context.register(LocalDataSourceConfig.class);
		context.refresh();
		assertThat(context.containsBean(CONFIG_BEAN_NAME), is(false));
	}
	
	@Test
	public void existsWithDevProfile(){
		initializeWithProfile(DEV_PROFILE_NAME);
		assertThat(context.containsBean(CONFIG_BEAN_NAME), is(true));
	}
	
	@Test
	public void existsWithProdProfile(){
		initializeWithProfile(PROD_PROFILE_NAME);
		assertThat(context.containsBean(CONFIG_BEAN_NAME), is(true));
	}
	
	@Test
	public void doesNotExistWithCfProfile(){
		initializeWithProfile("cloud");
		assertThat(context.containsBean(CONFIG_BEAN_NAME), is(false));
	}
	
	@Test
	public void requiresExpressionsResolved(){
		initializeWithProfile(DEV_PROFILE_NAME);
		verify(mockResolver, times(4))
			.resolveStringValue(argThat(org.hamcrest.Matchers.startsWith("${datastore")));
	}
}	
