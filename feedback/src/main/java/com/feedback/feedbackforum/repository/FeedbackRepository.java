package com.feedback.feedbackforum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.feedback.feedbackforum.model.Feedback;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    // Custom queries
    List<Feedback> findByUsername(String username);
    List<Feedback> findByType(String type);
}
