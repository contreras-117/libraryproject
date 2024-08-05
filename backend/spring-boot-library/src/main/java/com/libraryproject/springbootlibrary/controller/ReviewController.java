package com.libraryproject.springbootlibrary.controller;

import com.libraryproject.springbootlibrary.controller.model.ReviewRequest;
import com.libraryproject.springbootlibrary.service.ReviewService;
import com.libraryproject.springbootlibrary.utils.ExtractJWT;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PutMapping("/secure")
    public void postReview(@RequestHeader(value = "Authorization") String token,
                            @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = ExtractJWT.payloadExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }

        reviewService.postReview(userEmail, reviewRequest);
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value = "Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }

        return reviewService.userReviewListed(userEmail, bookId);
    }
}
