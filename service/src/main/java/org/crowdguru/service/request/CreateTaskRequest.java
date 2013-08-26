package org.crowdguru.service.request;

import java.util.List;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.User;

public class CreateTaskRequest {

	private String title;
	
	private String shortDescription;
	
	private String longDescription;
	
	private List<Long> specialisms;
	
	private int amount;
	
	private String unit;
	
	private boolean publish;
	
	private byte[] photo;
	
	private Long causeId;
	
	private String keyContactEmail;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public List<Long> getSpecialisms() {
		return specialisms;
	}

	public void setSpecialisms(List<Long> specialisms) {
		this.specialisms = specialisms;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public Long getCauseId() {
		return causeId;
	}

	public void setCauseId(Long causeId) {
		this.causeId = causeId;
	}

	public String getkeyContactEmail() {
		return keyContactEmail;
	}

	public void setKeyContactEmail(String keyContactEmail) {
		this.keyContactEmail = keyContactEmail;
	}
}
