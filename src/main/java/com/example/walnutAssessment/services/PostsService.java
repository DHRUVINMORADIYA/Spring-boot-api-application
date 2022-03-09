package com.example.walnutAssessment.services;

import com.example.walnutAssessment.DAO.PostDao;
import com.example.walnutAssessment.models.Post;
import com.example.walnutAssessment.utils.PostComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class PostsService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public SortedSet<Post> getPosts(String[] tags, String sortBy, String direction) {
        SortedSet<Post> posts = new TreeSet<>(new PostComparator(sortBy, direction));

        Arrays.stream(tags).forEach(tag -> {
            String url = "https://api.hatchways.io/assessment/solution/posts?tags=" + tag;
            PostDao postsByTag = webClientBuilder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(PostDao.class)
                    .block();
            assert postsByTag != null;
            posts.addAll(postsByTag.getPosts());
        });
        return posts;
    }
}
