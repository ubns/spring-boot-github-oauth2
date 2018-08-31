package com.app.oauth2.service;

import com.app.oauth2.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public interface RepositoryService {
    Repository[] getUserRepo();
}
