package org.main.deshimulaclone.repository;

import org.main.deshimulaclone.model.Feedback;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    List<Feedback> findByFeedbackForContainingIgnoreCase(String feedbackFor);
    List<Feedback> findByFeedbackBy (String feedbackBy);

    Feedback findByFeedbackId(UUID feedbackId);
}
