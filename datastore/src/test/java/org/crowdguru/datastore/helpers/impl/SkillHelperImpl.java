package org.crowdguru.datastore.helpers.impl;

import org.crowdguru.datastore.constants.SkillConstants;
import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.helpers.SkillHelper;

public class SkillHelperImpl implements SkillHelper, SkillConstants {

	@Override
	public Skill skill1() {
		Skill skill = new Skill();
		skill.setName(SKILL_1_NAME);
		return skill;
	}
	
	@Override
	public Skill skill2() {
		Skill skill = new Skill();
		skill.setName(SKILL_2_NAME);
		return skill;
	}
	
	@Override
	public Skill skill3() {
		Skill skill = new Skill();
		skill.setName(SKILL_3_NAME);
		return skill;
	}

}
