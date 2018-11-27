package com.itacademy.jd2.ikarotki.rwmanager.web.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ExtendedUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private Integer id;
	private String name;

	public ExtendedUsernamePasswordAuthenticationToken(final String name, final Integer id, final Object principal,
			final Object credentials, final Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		setId(id);
		setName(name);
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
