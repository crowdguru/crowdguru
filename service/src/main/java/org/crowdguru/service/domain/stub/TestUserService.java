package org.crowdguru.service.domain.stub;

import java.util.List;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.UserService;

public class TestUserService implements UserService{

	private TestRepository<User> repository;
	
	public TestUserService() {
		log().warn("state=created");
		repository = new TestRepository<User>();
	}

	@Override
	public synchronized User save(User user) {
		return repository.save(user);
	}

	@Override
	public synchronized User getUserByEmail(String email) {
		User user = null;
		List<User> users = repository.findAll();
		
		for(User usr: users) {
			if(usr.getEmail().equals(email)) {
				user = usr;
				break;
			}
		}
		
		return user;
	}
}
