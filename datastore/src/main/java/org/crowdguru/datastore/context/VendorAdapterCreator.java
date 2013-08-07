package org.crowdguru.datastore.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile({ "dev", "prod", "cloud" })
public class VendorAdapterCreator {

	public VendorAdapterCreator() {
		log().warn("state=created");
	}
	
	@Bean(name="vendorAdapter")
	public JpaVendorAdapter create() {
		log().info("activity=create");
		return new HibernateJpaVendorAdapter();
	}
}
