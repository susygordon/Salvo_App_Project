package com.codeoftheweb.salvo.service.impl;

import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
* Este service lo creamos para poder usarlo con el tema de la authenticacion. (Es decir, es por el asunto de seguridad).
* */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return playerRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
