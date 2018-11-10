package com.itacademy.jd2.ikarotki.rwmanager.web.security;

import java.util.ArrayList;
import java.util.List;

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

	// TODO inject UserService
	@Autowired
	public CustomAuthenticationProvider(IUserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String eMail = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";

		// TODO find use by login
		if (!"admin".equals(eMail)) {
			if (userAccountService.checkPassword(eMail, password)) {
				IUserAccount userAccount = userAccountService.getByEmail(eMail);
				final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(userAccount.getRole().toString()));
				return new ExtendedUsernamePasswordAuthenticationToken(userAccount.getId(), eMail, password,
						authorities);
			}
			throw new BadCredentialsException("1000");
		}

		// TODO verify password (DB contains hasn - not a plain password)
		if (!"nimda".equals(password)) {
			throw new BadCredentialsException("1000");
		}

		final int userId = 1; // FIXME: it should be the real user id from DB

		List<String> userRoles = new ArrayList<>();// TODO get list of user's
													// roles
		userRoles.add("ROLE_ADMIN");

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}
		return new ExtendedUsernamePasswordAuthenticationToken(userId, eMail, password, authorities);

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
