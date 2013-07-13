package org.crowdguru.datastore.context;

import org.crowdguru.datastore.repositories.UserRepository;
import org.springframework.context.ConfigurableApplicationContext;

import junit.framework.TestCase;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SingletonsTest extends TestCase{

	public void testCreatesContext(){
		ConfigurableApplicationContext context = Singletons.context();
		assertThat(context, is(notNullValue()));
	}
	
	public void testCreatesOneContext(){
		ConfigurableApplicationContext context1 = Singletons.context();
		ConfigurableApplicationContext context2 = Singletons.context();
		
		assertThat(context1, is(sameInstance(context2)));
	}
	
	public void testCreatesUserRepository(){
		
		UserRepository repo = Singletons.userRepository();
		assertThat(repo, is(notNullValue()));
	}

	public void testCreatesSameInstance(){
		
		UserRepository repo1 = Singletons.userRepository();
		UserRepository repo2 = Singletons.userRepository();
		
		assertThat(repo1, is(sameInstance(repo2)));
	}
}
