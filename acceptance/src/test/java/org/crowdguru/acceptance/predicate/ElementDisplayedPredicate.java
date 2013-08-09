package org.crowdguru.acceptance.predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementDisplayedPredicate extends OneParameterPredicate<String>{

	public ElementDisplayedPredicate(String xpath){
		super(xpath);
	}
	
	@Override
	public boolean apply(WebDriver driver) {
		return driver.findElement(By.xpath(parameter)).isDisplayed();
	}

}
