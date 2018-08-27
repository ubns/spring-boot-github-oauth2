package com.app.oauth2.repository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Component;

@Data
@Component
public class Repository {

    private Long id;

    private String node_id;

    private String name;

    private String html_url;

    private String language;

}
