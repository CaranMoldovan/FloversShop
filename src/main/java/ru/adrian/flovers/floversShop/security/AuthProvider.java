package ru.adrian.flovers.floversShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.adrian.flovers.floversShop.Services.PersonDetailsServices;

import java.util.Collection;
import java.util.Collections;

@Component

public class AuthProvider  implements AuthenticationProvider {
    private final PersonDetailsServices personDetailsServices;
@Autowired
    public AuthProvider(PersonDetailsServices personDetailsServices) {
        this.personDetailsServices = personDetailsServices;
    }

    @Override

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();

        UserDetails userDetails;
        try {
            userDetails = personDetailsServices.loadUserByUsername(name);
        } catch (Exception e) {
            throw new BadCredentialsException("User not found");
        }

        String password = authentication.getCredentials().toString();

        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
