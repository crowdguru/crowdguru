package org.crowdguru.datastore.domain.itds;

import org.crowdguru.datastore.domain.HasId;

public privileged aspect HasIdItds {

	public Long HasId.getId() {
		return id;
	}

	public void HasId.setId(Long id) {
		this.id = id;
	}
}
