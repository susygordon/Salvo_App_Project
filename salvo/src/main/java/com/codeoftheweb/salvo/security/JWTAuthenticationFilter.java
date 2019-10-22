package com.codeoftheweb.salvo.security;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static com.codeoftheweb.salvo.security.SecurityConstants.HEADER_STRING;
import static com.codeoftheweb.salvo.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private PlayerRepository playerRepository;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		//Le declaro el endpoint donde va a correr este filter
		setFilterProcessesUrl("/users/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
												HttpServletResponse res) throws AuthenticationException {
		//Intento hacer algo, sino, tiro una exception o la manejo de alguna forma
		//O suerte campeon al metodo que me llame
		try {
			if (playerRepository == null) {
				//Obtengo el contexto del servlet :D
				ServletContext servletContext = req.getServletContext();
				// Le pasamos al WebApplicationContextUtils Para obtener el WebApplication context a partir del Servet context
				WebApplicationContext webApplicationContext = WebApplicationContextUtils
						.getWebApplicationContext(servletContext);
				//Como es nulo obtengo el contexto me traigo el bean del contexto de la applicacion :)
				playerRepository = webApplicationContext.getBean(PlayerRepository.class);
			}
			//Crean que esto converte lo que vione del request en una objeto del tipo player
			Player player = new ObjectMapper()
					.readValue(req.getInputStream(), Player.class);
			// Llamamos al authentication manager para que nos autentique... de onda ;). Lo pasamos como UsernamePasswordAuthentication token, porque, por lo general, lo tiene definido de esa forma. Implementa una interfaz llamada Authentication.
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							player.getEmail(),
							player.getGamePlayers(),
							new ArrayList<>())
			);
		} catch (IOException e) {
			//Si algo falla tiramos una runtime exception como champions :)
			//TODO esto no se debe hacer :D
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws IOException, ServletException {

		String token = JWTRepository.getInstance().create(((User) auth.getPrincipal()).getUsername(), !((User) auth.getPrincipal()).getAuthorities().isEmpty());

		JWTRepository.getInstance().addToken(token);


		//Obtengo el player que estoy intentando que se autentique
		Optional<Player> player = playerRepository
				.findByUserName(((Player) auth.getPrincipal()).getEmail());

		//Si encontre uno ejecuto le setteo el ultimo logueo
		if (player.isPresent()) {
			player.get().setEmail(new Date());
			playerRepository.save(player.get());
		} else {
			throw new ServletException();
		}

		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}
