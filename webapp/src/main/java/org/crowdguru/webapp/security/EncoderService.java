package org.crowdguru.webapp.security;

public interface EncoderService {

    String encode(CharSequence raw);

    boolean matches(CharSequence raw, String encoded);
}
