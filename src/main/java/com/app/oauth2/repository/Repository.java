package com.app.oauth2.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private Long id;

    @JsonProperty()
    private String nodeId;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("private")
    private String priv;

    @JsonProperty("html_url")
    private String htmlUrl;

    private String description;

    private String fork;

    private String url;

    @JsonProperty("archive_url")
    private String archiveUrl;

    @JsonProperty("assignees_url")
    private String assigneesUrl;

    @JsonProperty("blobs_url")
    private String blobsUrl;

    @JsonProperty("branches_url")
    private String branchesUrl;

    private String language;

    @JsonProperty("forks_count")
    private Long forksCount;

    @JsonProperty("stargazers_count")
    private Long stargazersCount;

    @JsonProperty("watchers_count")
    private Long watchersCount;

    private Long size;

    @JsonProperty("default_branch")
    private String defaultBranch;

    @JsonProperty("open_issues_count")
    private Long openIssuesCount;

    @JsonProperty("pushed_at")
    private Date pushedAt;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
