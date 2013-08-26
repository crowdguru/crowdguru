package org.crowdguru.webapp.security;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityAccessor {

	public SecurityAccessor(){
		log().info("activity=create");
	}
	
	public boolean isAuthenticated(){
		return getAuthentication().isAuthenticated();
	}
	
	public boolean isCurrentUserAnonymous(){
		Object principal = getPrincipal();
		return isPrincipalForAnonymousUser() &&
		    principal.toString() == "anonymousUser";
	}
	
	public boolean isCurrentUserKeyContact() {
		return isCurrentUserHasRole(AuthorityService.Type.ROLE_KEYCONTACT);
	}
	
	public boolean isCurrentUserGuru() {
		return isCurrentUserHasRole(AuthorityService.Type.ROLE_GURU);
	}

	public String getCurrentUserEmail() {
		return getAuthentication().getName();
	}
	
	private boolean isCurrentUserHasRole(AuthorityService.Type type){
		Collection<GrantedAuthority> auths = getAuthorities();
		return isPrincipalForAuthenticatedUser() &&
		    isRolePresent(auths, type);
	}
	
	private Object getPrincipal(){
		return getAuthentication().getPrincipal();
	}
	
	@SuppressWarnings("unchecked")
	private Collection<GrantedAuthority> getAuthorities(){
		return (Collection<GrantedAuthority>) getAuthentication().getAuthorities();
	}
	
	private Authentication getAuthentication(){
		return getContext().getAuthentication();
	}
	
	private SecurityContext getContext(){
		return SecurityContextHolder.getContext();
	}
	
	private boolean isRolePresent(Collection<GrantedAuthority> auths, AuthorityService.Type type){
	    boolean isRolePresent = false;
	    
	    for (GrantedAuthority auth : auths) {
	      isRolePresent = auth.getAuthority().equals(type.toString());
	      
	      if (isRolePresent) break;
	    }
	    
	    return isRolePresent;
	}
	
	private boolean isPrincipalForAuthenticatedUser(){
		return isPrincipalInstanceOf(UserDetails.class);
	}
	
	private boolean isPrincipalForAnonymousUser(){
		return isPrincipalInstanceOf(String.class);
	}
	
	private <T> boolean isPrincipalInstanceOf(Class<?> type){
		Object principal = getPrincipal();
		return principal != null && principal.getClass().isAssignableFrom(type);
	}
}
