package org.main.deshimulaclone.service;


import lombok.RequiredArgsConstructor;
import org.main.deshimulaclone.model.Feedback;
import org.main.deshimulaclone.repository.FeedbackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    //    without pagination
//    public List<Feedback> getFeedback(){
//
//        // Fetch all Feedback records from the repository (database)
//        List<Feedback> feedbacks = feedbackRepository.findAll();
//        // Iterate over each Feedback and replace its message with a truncated message
//        for (Feedback feedback : feedbacks){
//            feedback.setMessage(truncateMessage(feedback.getMessage()));
//        }
//        // Return the (modified) list of feedbacks to the caller
//        return feedbacks;
//    }

    //    Implementing pagination
    public Page<Feedback> getFeedback(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);

        for (Feedback feedback : feedbacks){
            feedback.setMessage(truncateMessage(feedback.getMessage()));
        }

        return feedbacks;

    }

    // finding queries by name
    public List<Feedback> searchFeedbackByName (String feedbackFor){
        List<Feedback> feedbacks = feedbackRepository.findByFeedbackForContainingIgnoreCase(feedbackFor);

        for (Feedback feedback : feedbacks){
            feedback.setMessage(truncateMessage(feedback.getMessage()));
        }

        return feedbacks;
    }

    // finding queries by user
    public List<Feedback> searchFeedbackByUser (String feedbackBy){
        List<Feedback> feedbacks = feedbackRepository.findByFeedbackBy(feedbackBy);

        for (Feedback feedback : feedbacks){
            feedback.setMessage(truncateMessage(feedback.getMessage()));
        }

        return feedbacks;
    }

    public String truncateMessage(String msg){
        if (msg != null && msg.length() > 200){
            return msg.substring(0,200) + "...";
        }
        else return msg;
    }

    public void deleteByFeedbackId(UUID feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public void saveFeedback(Feedback feedback){
        feedbackRepository.save(feedback);
    }


    public Feedback getFeedbackByFeedbackId(UUID feedbackId){
        return feedbackRepository.findByFeedbackId(feedbackId);
    }
}
