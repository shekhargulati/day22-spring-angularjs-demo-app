package com.getbookmarks.domain;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stories")
public class Story {

    @Id
    private String id;

    private String title;

    private String text;

    private String url;

    private String[] tags;

    private String fullname;

    private final Date submittedOn = new Date();

    private String image;

    public Story() {
    }

    public Story(String url, String[] tags, String fullname) {
        this.url = url;
        this.tags = tags;
        this.fullname = fullname;
    }

    public Story(Story input, Story extraInformation) {
        this.url = input.getUrl();
        this.fullname = input.getFullname();
        this.tags = input.getTags();
        this.title = extraInformation.getTitle();
        this.text = extraInformation.getText();
        this.image = extraInformation.getImage();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
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

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Story [id=" + id + ", title=" + title + ", text=" + text + ", url=" + url + ", tags="
                + Arrays.toString(tags) + ", fullname=" + fullname + ", submittedOn=" + submittedOn + ", image="
                + image + "]";
    }

}
