package org.crowdguru.service.domain;

public interface EncoderService {

    String encode(CharSequence raw);

    boolean matches(CharSequence raw, String encoded);
}
