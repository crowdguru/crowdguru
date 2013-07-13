package org.crowdguru.datastore.repositories;

import org.crowdguru.datastore.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
