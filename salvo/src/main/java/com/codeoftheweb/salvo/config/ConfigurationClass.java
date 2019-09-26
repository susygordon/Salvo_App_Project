package com.codeoftheweb.salvo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigurationClass {

	// Creo el bean object mapper que probablemente usemos mas adelante. Capaz no. Pero por las dudas. Dejemoslo aca.
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}


	//Password encoder: Sirve para encriptar las password
	//Creo el bean para no tener que instanciarlo cada vez que lo necesite.
	//No tiene un estado ni atributo que deba modificarse. Por eso no hay problema que sea
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	// Configuramos el cors para que se pueda acceder desde otro servidor.
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").exposedHeaders("Authorization");
			}
		};
	}

}

