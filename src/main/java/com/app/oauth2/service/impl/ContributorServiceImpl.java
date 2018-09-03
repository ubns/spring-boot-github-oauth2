package com.app.oauth2.service.impl;

import com.app.oauth2.repository.Contributor;
import com.app.oauth2.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ContributorServiceImpl implements ContributorService {

    @Autowired
    private OAuth2RestTemplate auth2RestTemplate;

    @Override
    public Contributor[] getRepoContributor(String owner, String repo) {
        URI uri = UriComponentsBuilder.fromUriString("https://api.github.com/repos/" + owner + "/" + repo + "/stats/contributors").build().toUri();
        Contributor[] list = auth2RestTemplate.getForEntity(uri, Contributor[].class).getBody();
        return list;
    }
}
