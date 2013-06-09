package org.crowdguru.webapp.builders;

public interface Builder<S, T> {
	T build(S source);
}
