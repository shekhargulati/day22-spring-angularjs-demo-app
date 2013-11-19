package com.getbookmarks.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stories")
public class Story {

    @Id
    private String id;

    private String title;

    private String excerpt;

    private String url;

    private String[] tags;

    private String fullname;

    private final Date submittedOn = new Date();

    public Story() {
    }

    public Story(String title, String excerpt, String url, String[] tags, String fullname) {
        super();
        this.title = title;
        this.excerpt = excerpt;
        this.url = url;
        this.tags = tags;
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getUrl() {
        return url;
    }

    public String[] getTags() {
        return tags;
    }

    public String getFullname() {
        return fullname;
    }

    public Date getSubmittedOn() {
        return submittedOn;
    }

}
