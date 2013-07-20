package org.crowdguru.webapp.builder;

public interface Builder<S, T> {
	T build(S source);
}
