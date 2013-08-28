package org.crowdguru.webapp.helper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.security.Principal;
import org.crowdguru.webapp.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.DelegatingFilterProxy;

public class AuthorityFactory {

	public static Authentication createAnoymousAuthority(){
		Principal principal = mock(Principal.class);
		return putStubPrincipalInPlace(principal);
	}

	public static Authentication createKeyContactAuthority() {
		UserDetails principal = mock(UserDetails.class);
		prepareBaseUserDetailsPrincipal(principal);
		return putStubPrincipalInPlace(principal);
	}
	
	public static Authentication createGuruAuthority() {
		UserDetails principal = mock(UserDetails.class);
		prepareBaseUserDetailsPrincipal(principal);
		return putStubPrincipalInPlace(principal);
	}
	
	private static UserDetails prepareBaseUserDetailsPrincipal(UserDetails principal){
		return principal;
	}
	
	private static Authentication putStubPrincipalInPlace(Object principal){
		Authentication authenticaton = mock(Authentication.class);
		when(authenticaton.getPrincipal()).thenReturn(principal);
		return authenticaton;
	}
}
