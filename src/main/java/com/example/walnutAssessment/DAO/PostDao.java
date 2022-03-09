package com.example.walnutAssessment.DAO;

import com.example.walnutAssessment.models.Post;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class PostDao {
    private final List<Post> posts = new LinkedList<>();
    public List<Post> getPosts() {
        return posts;
    }

}
