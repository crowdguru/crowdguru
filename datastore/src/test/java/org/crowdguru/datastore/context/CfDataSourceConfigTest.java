package org.crowdguru.datastore.context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringValueResolver;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CfDataSourceConfigTest{

	/**
	 * Profile for cloud environment
	 */
	private static final String CF_PROFILE_NAME = "cloud";
	
	/**
	 * The bean under test
	 */
	private static final String CONFIG_BEAN_NAME = "cfDataSourceConfig";
	
	/**
	 * For resolving SpEL expressions while beans are being
	 * Initialised
	 */
	@Mock
	private StringValueResolver mockResolver;
	
	private AnnotationConfigApplicationContext context;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		
	@Before
	public void setUp(){
		context = new AnnotationConfigApplicationContext();
		
		Mockito.when(mockResolver.resolveStringValue("${cf.mysql.servicename}"))
			.thenReturn("SERVICE_NAME");
	}

	private void initializeWithProfile(String profile) {
		context.getEnvironment().setActiveProfiles(profile);
		context.register(CfDataSourceConfig.class);
		context.getBeanFactory().addEmbeddedValueResolver(mockResolver);
		context.refresh();
	}
	
	@Test
	public void doesNotExistWithoutProfile(){
		context.register(CfDataSourceConfig.class);
		context.refresh();
		assertThat(context.containsBean(CONFIG_BEAN_NAME), is(false));
	}
	
	@Test
	public void existsWithProfile(){
		thrown.expect(BeanCreationException.class);
		thrown.expectMessage("NullPointer");
		initializeWithProfile(CF_PROFILE_NAME);
	}
	
	@Test
	public void requiresExpressionResolved(){
		thrown.expect(BeanCreationException.class);
		initializeWithProfile(CF_PROFILE_NAME);
		verify(mockResolver)
			.resolveStringValue(argThat(is("${cf.mysql.servicename}")));
	}
}
