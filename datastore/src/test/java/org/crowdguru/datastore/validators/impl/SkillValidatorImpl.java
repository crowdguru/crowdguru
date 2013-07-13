package org.crowdguru.datastore.validators.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.crowdguru.datastore.constants.SkillConstants;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.validators.SkillValidator;

public class SkillValidatorImpl implements SkillConstants, SkillValidator {

	@Override
	public void validateSkill1(Skill skill) {
		assertThat(skill, is(notNullValue()));
		assertThat("name", skill.getName(), is(equalTo(SKILL_1_NAME)));
	}
	
	@Override
	public void validateSkill2(Skill skill) {
		assertThat(skill, is(notNullValue()));
		assertThat("name", skill.getName(), is(equalTo(SKILL_2_NAME)));
	}
}
