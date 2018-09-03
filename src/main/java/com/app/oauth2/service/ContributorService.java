package com.app.oauth2.service;

import com.app.oauth2.repository.Contributor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorService {
    Contributor[] getRepoContributor(String owner, String repo);
}
