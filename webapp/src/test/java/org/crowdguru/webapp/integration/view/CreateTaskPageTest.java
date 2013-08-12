package org.crowdguru.webapp.integration.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.SkillGroup;
import org.crowdguru.service.domain.TaskService;
import org.crowdguru.webapp.helper.AuthorityFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

public class CreateTaskPageTest extends PageTest {
	
	private static final String path = "/tasks/new";
	
	@Autowired
	private TaskService taskService;
	
	@Before
	public void setAuthority(){
		setAuthority(AuthorityFactory.createKeyContactAuthority());
	}
	
	@Test
	public void  titleInputExists() throws Exception{
		this.mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='title']").exists());
	}
	
	@Test
	public void  shortDescriptionTextareaExists() throws Exception{
		this.mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(xpath("//textarea[@name='shortDescription']").exists());
	}
	
	@Test
	public void  longDescriptionTextareaExists() throws Exception{
		this.mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(xpath("//textarea[@name='longDescription']").exists());
	}
	
	@Test
	public void  timeFrameAmountSelectionExists() throws Exception{
		this.mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(xpath("//select[@name='amount']").exists());
	}
	
	@Test
	public void  specialismCheckboxesExist() throws Exception{
		List<SkillGroup> groups = new ArrayList<SkillGroup>();
		SkillGroup group = new SkillGroup();
		group.setId(new Long(1));
		group.setGroupName("Group 1");
		Skill skill = new Skill();
		skill.setId(new Long(2));
		skill.setName("Skill 1");
		Set<Skill> skills = new HashSet<Skill>();
		skills.add(skill);
		group.setSkills(skills);
		groups.add(group);
		when(taskService.getSkillGroups()).thenReturn(groups);
		this.mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@name='specialisms']").exists());
	}
	
	@Test
	public void  saveButtonExists() throws Exception{
		this.mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(xpath("//button[@id='buttonSave']").exists());
	}
	
	@Test
	public void  publishButtonExists() throws Exception{
		this.mockMvc.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(xpath("//button[@id='buttonPublish']").exists());
	}
}

