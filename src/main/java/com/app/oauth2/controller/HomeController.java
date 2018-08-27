package com.app.oauth2.controller;

import com.app.oauth2.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.security.Principal;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private OAuth2RestTemplate auth2RestTemplate;

    @Autowired
    private MessageSource ms;

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", ms.getMessage("page.title.top", null, Locale.JAPAN));
        return "home";
    }

    @GetMapping("/repos")
    public String repositories(Model model) {
        URI uri = UriComponentsBuilder.fromUriString("https://api.github.com/user/repos").build().toUri();
        model.addAttribute("repos", auth2RestTemplate.getForEntity(uri, Repository[].class).getBody());
        return "repos";
    }

    @GetMapping("/{username}")
    public String login(@PathVariable("username") String name, Model model, Principal principal, RedirectAttributes attributes) {

        if (principal == null) {
            attributes.addFlashAttribute("error", ms.getMessage("error.message.token", null, Locale.JAPAN));
            return "redirect:/";
        }

        Authentication auth = (Authentication)principal;
        String userName = (String)auth.getPrincipal();

        if (!name.equals(userName)) {
            attributes.addFlashAttribute("error", ms.getMessage("error.message.url", null, Locale.JAPAN));
            return "redirect:/";
        }

        // リポジトリ情報取得
        URI uri = UriComponentsBuilder.fromUriString("https://api.github.com/users/" + userName + "/repos").build().toUri();


        model.addAttribute("repos", auth2RestTemplate.getForEntity(uri, Repository[].class).getBody());
        model.addAttribute("title", ms.getMessage("page.title.dashboard", new String[]{name}, Locale.JAPAN));
        return "user/dashboard";
    }
}
