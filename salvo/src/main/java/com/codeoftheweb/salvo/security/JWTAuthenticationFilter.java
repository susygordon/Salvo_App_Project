package com.codeoftheweb.salvo.security;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
		setFilterProcessesUrl("/users/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res) throws AuthenticationException {
		try {
			if (playerRepository == null) {
				ServletContext servletContext = req.getServletContext();
				WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
				playerRepository = webApplicationContext.getBean(PlayerRepository.class);
			}

			Player player = new ObjectMapper()
					.readValue(req.getInputStream(), Player.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							player.getUsername(),
							player.getPassword(),
							new ArrayList<>())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
	                                        HttpServletResponse res,
	                                        FilterChain chain,
	                                        Authentication auth) throws IOException, ServletException {

		String token = JWTRepository.getInstance().create(((Player) auth.getPrincipal()).getUsername(), !((Player) auth.getPrincipal()).getAuthorities().isEmpty());

		JWTRepository.getInstance().addToken(token);

		Optional<Player> user = playerRepository
				.findByUserName(((Player) auth.getPrincipal()).getUserName());

		if (user.isPresent()) {
			user.get().setLastLogin(new Date());
			playerRepository.save(user.get());
		}

		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}
