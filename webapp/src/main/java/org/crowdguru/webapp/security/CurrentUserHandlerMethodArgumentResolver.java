package org.crowdguru.webapp.security;

import java.security.Principal;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

     @Override
     public boolean supportsParameter(MethodParameter methodParameter) {
          return methodParameter.getParameterType().equals(UserDetails.class);
     }

     @Override
     public Object resolveArgument(MethodParameter methodParameter,
                         ModelAndViewContainer mavContainer,
                         NativeWebRequest webRequest,
                         WebDataBinderFactory binderFactory) throws Exception {

          if (this.supportsParameter(methodParameter)) {
              Principal principal = webRequest.getUserPrincipal();
              return (UserDetails) ((Authentication) principal).getPrincipal();
          } else {
              return WebArgumentResolver.UNRESOLVED;
          }
     }
}
