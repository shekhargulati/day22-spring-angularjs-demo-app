package com.getbookmarks.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.getbookmarks.domain.Story;
import com.getbookmarks.repository.StoryRepository;

@Controller
@RequestMapping("/stories")
public class StoryResource {

    private StoryRepository storyRepository;

    @Autowired
    public StoryResource(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> submitStory(@RequestBody Story story) {
        Story storyWithExtractedInformation = decorateWithInformation(story);
        storyRepository.save(storyWithExtractedInformation);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Story> allStories() {
        return storyRepository.findAll();
    }

    @RequestMapping(value = "/{storyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Story showStory(@PathVariable("storyId") String storyId) {
        Story story = storyRepository.findOne(storyId);
        if (story == null) {
            throw new StoryNotFoundException(storyId);
        }
        return story;
    }

    private Story decorateWithInformation(Story story) {
        String url = story.getUrl();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Story> forEntity = restTemplate.getForEntity(
                "http://gooseextractor-t20.rhcloud.com/api/v1/extract?url=" + url, Story.class);
        if (forEntity.hasBody()) {
            return new Story(story, forEntity.getBody());
        }
        return story;

    }

}
