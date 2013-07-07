package org.crowdguru.datastore.context;

import org.crowdguru.datastore.repositories.UserRepository;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SingletonsTest{

	@Test
	public void testCreatesContext(){
		ConfigurableApplicationContext context = Singletons.context();
		assertThat(context, is(notNullValue()));
	}
	
	@Test
	public void testCreatesOneContext(){
		ConfigurableApplicationContext context1 = Singletons.context();
		ConfigurableApplicationContext context2 = Singletons.context();
		
		assertThat(context1, is(sameInstance(context2)));
	}
	
	@Test
	public void testCreatesUserRepository(){
		
		UserRepository repo = Singletons.userRepository();
		assertThat(repo, is(notNullValue()));
	}

	@Test
	public void testCreatesSameInstance(){
		
		UserRepository repo1 = Singletons.userRepository();
		UserRepository repo2 = Singletons.userRepository();
		
		assertThat(repo1, is(sameInstance(repo2)));
	}
}
