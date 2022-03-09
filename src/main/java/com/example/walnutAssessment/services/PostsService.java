package com.example.walnutAssessment.services;

import com.example.walnutAssessment.DAO.PostDao;
import com.example.walnutAssessment.models.Post;
import com.example.walnutAssessment.utils.PostComparator;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class PostsService {

    PostDao postDao = new PostDao();
    RestTemplate restTemplate = new RestTemplate();

    public SortedSet<Post> getPosts(String[] tags, String sortBy, String direction) {
        SortedSet<Post> posts = new TreeSet<>(new PostComparator(sortBy,direction));
        for (String tag : tags) {
            String url = "https://api.hatchways.io/assessment/solution/posts?tags=" + tag;
            PostDao postsByTag = restTemplate.getForObject(url, PostDao.class);
            if (postsByTag != null)
                posts.addAll(postsByTag.getPosts());
        }
        return posts;
    }
}
