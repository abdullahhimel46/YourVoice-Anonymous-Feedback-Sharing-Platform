package org.main.deshimulaclone.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.main.deshimulaclone.model.Feedback;
import org.main.deshimulaclone.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@EnableWebSecurity // to enable @PreAuthorize
@Controller
public class AppController {
    private final FeedbackService feedbackService;

    @GetMapping({"/","/home","/home-page"})
    public String homePage(@RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest httpServletRequest){ // model is used to pass the attributes to the UI
        Pageable pageable = PageRequest.of(page,2);
        Page<Feedback> feedbackPage = feedbackService.getFeedback(pageable);
        // passing the attribute values to the UI
        model.addAttribute("feedbacks",feedbackPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",feedbackPage.getTotalPages());

        if (httpServletRequest.isUserInRole("ADMIN")){
            model.addAttribute("isAdmin",true);
        }

        return "/home-page";
    }

    @GetMapping("/search")
    public String searchFeedback(@RequestParam String searchQuery, Model model){
        List<Feedback>    feedbacks = feedbackService.searchFeedbackByName(searchQuery);
        model.addAttribute("feedbacks",feedbacks);
        model.addAttribute("searchQuery",searchQuery);

        return "/home-page";
    }

    @GetMapping("/user/dashboard")
    public String userDashboardPage (Model model, HttpServletRequest httpServletRequest){
        // if user isn't logged in
        if (httpServletRequest.getUserPrincipal() == null){
            return "redirect:/login";
        }

        String username = httpServletRequest.getUserPrincipal().getName();
        List<Feedback> feedbacks = feedbackService.searchFeedbackByUser(username);
        model.addAttribute("feedbacks",feedbacks);

        return "/user-dashboard-page";
    }

    // fn to load a new feedback page
    @GetMapping("/feedback")
    public String postfeedbackPage(Model model) {
        // add a new obj of feedback every time
        model.addAttribute("feedback",new Feedback());
        model.addAttribute("mode","add");

        return "post-page";
    }

    //    save feedback
    @PostMapping("/feedback")
    public String saveFeedback(@ModelAttribute Feedback feedback, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes){
        // if user isn't logged in
        if (httpServletRequest.getUserPrincipal() == null){
            redirectAttributes.addFlashAttribute("message","You must login first!");
            return "redirect:/login";
        }
        String username = httpServletRequest.getUserPrincipal().getName();
        LocalDateTime currentTime = LocalDateTime.now();
        feedback.setFeedbackBy(username);
        feedback.setCreatedAt(currentTime);
        feedbackService.saveFeedback(feedback);

        return "redirect:/user/dashboard";
    }

    // method for viewing a full page review
    @GetMapping("/feedback/{feedbackId}")
    public String feedbackDetails(@PathVariable UUID feedbackId, Model model){
        Feedback feedback = feedbackService.getFeedbackByFeedbackId(feedbackId);
        model.addAttribute("feedback",feedback);

        return "/feedback-page";
    }

    // method to load the feedback edit page
    @GetMapping("/feedback/{feedbackId}/edit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String editFeedback (@PathVariable UUID feedbackId, Model model) {
        Feedback feedback = feedbackService.getFeedbackByFeedbackId(feedbackId);
        model.addAttribute("feedback",feedback);
        model.addAttribute("mode","edit");

        return "post-page";
    }

    // method to save the edited feedback
    @PostMapping("/feedback/{feedbackId}/edit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String doEditFeedback (@PathVariable UUID feedbackId, @ModelAttribute Feedback feedback, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        LocalDateTime currentTime = LocalDateTime.now();
        feedback.setFeedbackId(feedbackId);
        feedback.setFeedbackBy(username);
        feedback.setCreatedAt(currentTime);
        feedbackService.saveFeedback(feedback);

        return "redirect:/user/dashboard";
    }

    @PostMapping("/feedback/{feedbackId}/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteFeedback (@PathVariable UUID feedbackId, HttpServletRequest httpServletRequest) {
        feedbackService.deleteByFeedbackId(feedbackId);

        if (httpServletRequest.isUserInRole("ROLE_USER"))
            return "redirect:/user/dashboard";
        else
            return "redirect:/";
    }



}
