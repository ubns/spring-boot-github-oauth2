package com.app.oauth2.controller;

import com.app.oauth2.repository.Contributor;
import com.app.oauth2.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContributorController {

    @Autowired
    private MessageSource ms;

    @Autowired
    private ContributorService service;

    @GetMapping("/contributor/{owner}/{repo}")
    public String contributor(@PathVariable("owner") String owner, @PathVariable("repo") String repo, Model model) {
        Contributor[] contributors = service.getRepoContributor(owner, repo);

        model.addAttribute("contributors", contributors);
        return "user/contributor";
    }
}
