package com.itacademy.jd2.ikarotki.rwmanager.web.security;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private IUserAccountService userAccountService;

    @Autowired
    public CustomAuthenticationProvider(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String eMail = authentication.getPrincipal() + "";
        final String password = authentication.getCredentials() + "";

        IUserAccount userAccount = userAccountService.getByEmail(eMail);
        if (userAccount == null) {
            throw new BadCredentialsException("1000");
        }

        String hashed = userAccount.getPassword();
        if (!BCrypt.checkpw(password, hashed)) {
            throw new BadCredentialsException("1000");
        }

        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userAccount.getRole().toString()));
        return new ExtendedUsernamePasswordAuthenticationToken(userAccount.getFirstName(), userAccount.getId(), eMail,
                password, authorities);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
