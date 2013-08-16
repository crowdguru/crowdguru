package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Skill;
import org.crowdguru.datastore.domain.Task;

public privileged aspect TaskItds {

	public Long Task.getId() {
		return id;
	}

	public void Task.setId(Long id) {
		this.id = id;
	}
	
	public String Task.getTitle() {
		return title;
	}

	public void Task.setTitle(String title) {
		this.title = title;
	}
	
	public String Task.getShortDescription() {
		return shortDescription;
	}

	public void Task.setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String Task.getLongDescription(){
		return longDescription;
	}
	
	public void Task.setLongDescription(String longDescription){
		this.longDescription = longDescription;
	}
	
	public Set<Skill> Task.getSpecialisms(){
		return this.specialisms;
	}
	
	public void Task.setSpecialisms(Set<Skill> specialisms){
		this.specialisms = specialisms;
	}
	
	public int Task.getAmount() {
		return amount;
	}

	public void Task.setAmount(int amount) {
		this.amount = amount;
	}
	
	public String Task.getUnit() {
		return unit;
	}

	public void Task.setUnit(String unit) {
		this.unit = unit;
	}
	
	public byte[] Task.getPhoto(){
		return this.photo;
	}
	
	public void Task.setPhoto(byte[] photo){
		this.photo = photo;
	}
}
