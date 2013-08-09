package org.crowdguru.acceptance.predicate;

import org.openqa.selenium.WebDriver;

import com.google.common.base.Predicate;

public abstract class OneParameterPredicate<T> implements Predicate<WebDriver>{

	protected T parameter;
	
	public OneParameterPredicate(T parameter){
		this.parameter = parameter;
	}
}
