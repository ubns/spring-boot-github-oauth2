package com.app.oauth2.repository;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Contributor {

    private User author;

    private Long total;

    private Week weeks;
}
