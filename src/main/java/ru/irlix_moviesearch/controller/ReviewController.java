package ru.irlix_moviesearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.irlix_moviesearch.dto.ReviewDTO;
import ru.irlix_moviesearch.model.Review;
import ru.irlix_moviesearch.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewDTO review = reviewService.getById(id);
        return ResponseEntity.ok(review);
    }

    @PostMapping
    public ReviewDTO createReview(@RequestBody Review review,
                               @RequestParam Long movie_id,
                               @RequestParam Long critic_id) {
        return reviewService.create(review, movie_id, critic_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id,
                                               @RequestBody Review review) {
        Review updatedReview = reviewService.update(id, review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie/{movie_id}")
    public List<Review> getReviewsByMovie(@PathVariable Long movie_id) {
        return reviewService.getReviewsByMovie(movie_id);
    }
}
