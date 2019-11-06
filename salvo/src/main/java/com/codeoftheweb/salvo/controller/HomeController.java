package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.dto.UserPassword;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getLogin(Model model) {
        model.addAttribute("userPassword", new UserPassword());
        return "redirect:http://localhost:8080/web/login";
    }

    @PostMapping("/login")
    public String authenticate( UserPassword userPassword, ModelAndView modelAndView) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "http://localhost:8080/users/login", HttpMethod.POST, new HttpEntity<>(userPassword),
                new ParameterizedTypeReference<Map<String, Object>>(){});
        return "redirect:http://localhost:8080/web/games.html?token="+response.getHeaders().get("Authorization").get(0);
    }


}