package org.crowdguru.datastore.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class PropertyConfigurable {
	
	private Environment env;
	
	@Autowired
	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	protected String getProperty(String key) {
		String value = env.getProperty(key);
		log().info("key=" + key + ";value=" + value);
		return value;
	}
}
