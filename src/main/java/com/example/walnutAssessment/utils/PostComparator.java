package com.example.walnutAssessment.utils;

import com.example.walnutAssessment.models.Post;

import java.util.Comparator;
import java.util.Objects;

import static com.example.walnutAssessment.utils.Constants.*;

public class PostComparator implements Comparator<Post> {
    private final String sortBy;
    private final String direction;

    public PostComparator(String sortBy, String direction)
    {
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public int compare(Post p1, Post p2) {
        int negative=1;
        if(Objects.equals(direction, DESC))negative=-1;
        switch(sortBy)
        {
            case ID:
                return negative*(p1.getId()-p2.getId());
            case READS:
                return negative*(p1.getReads()-p2.getReads());
            case LIKES:
                return negative*(p1.getLikes()-p2.getLikes());
            case POPULARITY:
                if(negative*(p1.getPopularity() - p2.getPopularity())>0)return 1;
                else return -1;
        }
        return 0;
    }
}
