package com.feedback.feedbackforum.controller;

import com.feedback.feedbackforum.model.Feedback;
import com.feedback.feedbackforum.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/students")
    public ResponseEntity<List<Feedback>> getAllStudentFeedback() {
        List<Feedback> studentFeedbackList = feedbackService.getAllFeedbackByType("Student");
        return new ResponseEntity<>(studentFeedbackList, HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Feedback>> getAllTeacherFeedback() {
        List<Feedback> teacherFeedbackList = feedbackService.getAllFeedbackByType("Teacher");
        return new ResponseEntity<>(teacherFeedbackList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> allFeedbackList = feedbackService.getAllFeedback();
        return new ResponseEntity<>(allFeedbackList, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudentFeedback(@RequestBody Feedback feedback) {
        return saveFeedbackWithResponseType(feedback, "Student");
    }

    @PostMapping("/teachers")
    public ResponseEntity<String> createTeacherFeedback(@RequestBody Feedback feedback) {
        return saveFeedbackWithResponseType(feedback, "Teacher");
    }

    private ResponseEntity<String> saveFeedbackWithResponseType(Feedback feedback, String feedbackType) {
        try {
            feedback.setType(feedbackType);
            feedbackService.saveFeedback(feedback);
            return new ResponseEntity<>(feedbackType + " Feedback saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving " + feedbackType.toLowerCase() + " feedback: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
