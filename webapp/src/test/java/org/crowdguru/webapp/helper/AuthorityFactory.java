package org.crowdguru.webapp.helper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.crowdguru.datastore.domain.User.Type;
import org.crowdguru.service.domain.UserDetails;
import org.springframework.security.core.Authentication;

public class AuthorityFactory {

	public static Authentication createAnoymousAuthority(){
		Principal principal = mock(Principal.class);
		return putStubPrincipalInPlace(principal);
	}

	public static Authentication createKeyContactAuthority() {
		UserDetails principal = mock(UserDetails.class);
		when(principal.isGuru()).thenReturn(false);
		when(principal.isKeyContact()).thenReturn(true);
		when(principal.getType()).thenReturn(Type.KEYCONTACT);
		prepareBaseUserDetailsPrincipal(principal);
		return putStubPrincipalInPlace(principal);
	}
	
	public static Authentication createGuruAuthority() {
		UserDetails principal = mock(UserDetails.class);
		when(principal.isGuru()).thenReturn(true);
		when(principal.isKeyContact()).thenReturn(false);
		when(principal.getType()).thenReturn(Type.GURU);
		prepareBaseUserDetailsPrincipal(principal);
		return putStubPrincipalInPlace(principal);
	}
	
	private static UserDetails prepareBaseUserDetailsPrincipal(UserDetails principal){
		when(principal.getForename()).thenReturn("Forename");
		when(principal.getSurname()).thenReturn("Surname");
		when(principal.getEmail()).thenReturn("test@crowdguru.org");
		return principal;
	}
	
	private static Authentication putStubPrincipalInPlace(Object principal){
		Authentication authenticaton = mock(Authentication.class);
		when(authenticaton.getPrincipal()).thenReturn(principal);
		return authenticaton;
	}
}
