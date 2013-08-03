package org.crowdguru.service.domain.stub;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.service.domain.SkillService;

public class TestSkillRepository implements SkillService {

	private TestRepository<Skill> repository;
	
	public TestSkillRepository() {
		log().warn("state=created");
		repository = new TestRepository<Skill>();
	}

	@Override
	public Skill findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Skill save(Skill skill) {
		return repository.save(skill);
	}

}
