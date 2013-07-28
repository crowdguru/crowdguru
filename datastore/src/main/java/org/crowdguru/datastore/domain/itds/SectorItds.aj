package org.crowdguru.datastore.domain.itds;

import java.util.Set;

import org.crowdguru.datastore.domain.Cause;
import org.crowdguru.datastore.domain.Sector;

public privileged aspect SectorItds {

	public String Sector.getName() {
		return name;
	}

	public void Sector.setName(String name) {
		this.name = name;
	}

	public Set<Cause> Sector.getCauses(){
		return causes;
	}
	
	public void Sector.setCauses(Set<Cause> causes){
		this.causes = causes;
	}
	
	public String Sector.toString() {
		return "{ "
				+ "id: '"   + id + "', "
				+ "name: '" + name + "', " 
				+ "' }";
	}

}
