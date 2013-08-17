package org.crowdguru.acceptance.predicate;

import org.openqa.selenium.WebDriver;

public class PageLoadedPredicate extends OneParameterPredicate<String>{

	public PageLoadedPredicate(String url){
		super(url);
	}
	
	@Override
	public boolean apply(WebDriver driver) {
		boolean result = driver.getCurrentUrl().contains(parameter);
		log().debug("url=" + parameter + ";result=" + result);
		return result;
	}

}
