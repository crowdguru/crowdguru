package org.crowdguru.service.domain.impl;

import org.crowdguru.service.domain.EncoderService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile({"insecure"})
public class NoOpEncoderService implements EncoderService {

	private PasswordEncoder encoder;
	
	public NoOpEncoderService() {
		log().warn("state=created");
		encoder = NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	public String encode(CharSequence raw) {
		log().info("activity=encode;raw=" + raw);
		String encoded = encoder.encode(raw);
		log().debug("encoded=" + encoded);
		return encoded;
	}

	@Override
	public boolean matches(CharSequence raw, String encoded) {
		return encoder.matches(raw, encoded);
	}

}
