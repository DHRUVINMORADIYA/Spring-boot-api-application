package com.example.walnutAssessment.controllers;

import com.example.walnutAssessment.models.Post;
import com.example.walnutAssessment.services.PostsService;
import com.example.walnutAssessment.validators.PostsControllerValidators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Set;

@RestController
@Slf4j
public class fetchHatchwayAPIs {

    PostsControllerValidators postsControllerValidators = new PostsControllerValidators();
    @GetMapping("/api/ping")
    public ResponseEntity<Object> pingController() {
        log.info("ping");
        return ResponseEntity.ok().body(Collections.singletonMap("success",true));
    }

    @GetMapping("/api/posts")
    public ResponseEntity<Object> postsController(@RequestParam(name="tags",required=false) String[] tags, @RequestParam(name="sortBy", required=false, defaultValue = "popularity") String sortBy, @RequestParam(name="direction", required=false,defaultValue = "asc") String direction) {
        if(!postsControllerValidators.validateTags(tags))
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","Tags parameter is required"));
        if(!postsControllerValidators.validateSortBy(sortBy))
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","sortBy parameter is invalid"));
        if(!postsControllerValidators.validateDirection(direction))
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","direction is invalid"));
        PostsService postsService = new PostsService();
        return ResponseEntity.ok().body(postsService.getPosts(tags,sortBy,direction));
    }
}