package com.hmsapp.hmsapp.controller;

import com.hmsapp.hmsapp.Entity.Property;
import com.hmsapp.hmsapp.Entity.Reviews;
import com.hmsapp.hmsapp.payload.ProfileDto;
import com.hmsapp.hmsapp.repository.ReviewsRepository;
import com.hmsapp.hmsapp.Entity.User;
import com.hmsapp.hmsapp.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")

public class ReviewController {
    private PropertyRepository propertyRepository;
    private ReviewsRepository reviewsRepository;

    public ReviewController(PropertyRepository propertyRepository, ReviewsRepository reviewsRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    @PostMapping
    public String addReview(
            @RequestBody Reviews review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal User user
      )
    {
        Property property = propertyRepository.findById(propertyId).get();
        Reviews reviewsStatus = reviewsRepository.findByPropertyAndUser(property, user);
        if(reviewsStatus!= null) {
            review.setProperty(property);
            review.setUser(user);
            reviewsRepository.save(review);
            return "added";
        }return "Review already exists";
    }
    @GetMapping("user/reviews")
    public List<Reviews> viewMyReviews(
            @AuthenticationPrincipal User user

    )
    {
      return reviewsRepository.findByUser(user);

    }
    @GetMapping()
    public ResponseEntity<ProfileDto> getUserProfile(
            @AuthenticationPrincipal User user
    ){
       ProfileDto profileDto = new ProfileDto();
       profileDto.setUsername(user.getUsername());
       profileDto.setEmail(user.getEmail());
       profileDto.setName(user.getName());
       return new ResponseEntity<ProfileDto>(profileDto, HttpStatus.OK);
    }

}

