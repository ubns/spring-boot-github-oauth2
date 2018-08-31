package com.app.oauth2.service.impl;

import com.app.oauth2.repository.Repository;
import com.app.oauth2.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    private OAuth2RestTemplate auth2RestTemplate;

    @Override
    public Repository[] getUserRepo() {
        URI uri = UriComponentsBuilder.fromUriString("https://api.github.com/user/repos").build().toUri();
        return auth2RestTemplate.getForEntity(uri, Repository[].class).getBody();
    }
}
