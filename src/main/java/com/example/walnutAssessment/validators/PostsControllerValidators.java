package com.example.walnutAssessment.validators;

import java.util.Objects;

import static com.example.walnutAssessment.utils.Constants.*;

public class PostsControllerValidators {
    public Boolean validateTags(String[] tags) {
        return tags != null && tags.length >= 1;
    }

    public Boolean validateSortBy(String sortBy) {
        return Objects.equals(sortBy, ID) || Objects.equals(sortBy, READS) || Objects.equals(sortBy, LIKES) || Objects.equals(sortBy, POPULARITY);
    }

    public Boolean validateDirection(String direction) {
        return Objects.equals(direction, ASC) || Objects.equals(direction, DESC);
    }
}
