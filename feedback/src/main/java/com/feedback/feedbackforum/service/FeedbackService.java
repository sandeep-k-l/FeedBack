package com.feedback.feedbackforum.service;

import com.feedback.feedbackforum.model.Feedback;
import com.feedback.feedbackforum.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getStudentFeedback() {
        return feedbackRepository.findByType("Student");
    }

    public List<Feedback> getTeacherFeedback() {
        return feedbackRepository.findByType("Teacher");
    }

    public List<Feedback> getAllFeedbackByType(String feedbackType) {
        if ("all".equalsIgnoreCase(feedbackType)) {
            return getAllFeedback();
        } else if ("student".equalsIgnoreCase(feedbackType)) {
            return getStudentFeedback();
        } else if ("teacher".equalsIgnoreCase(feedbackType)) {
            return getTeacherFeedback();
        } else {
            // Handle unknown feedbackType as needed
            return Collections.emptyList();
        }
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    // Additional method for retrieving feedback by username
    public List<Feedback> getFeedbackByUsername(String username) {
        return feedbackRepository.findByUsername(username);
    }

    // Other methods
}
