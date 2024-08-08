package ru.irlix_moviesearch.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.irlix_moviesearch.dto.ReviewDTO;
import ru.irlix_moviesearch.model.Critic;
import ru.irlix_moviesearch.model.Movie;
import ru.irlix_moviesearch.model.Review;
import ru.irlix_moviesearch.repository.CriticRepository;
import ru.irlix_moviesearch.repository.MovieRepository;
import ru.irlix_moviesearch.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final CriticRepository criticRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<ReviewDTO> getAll() {
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        reviewRepository
                .findAll()
                .forEach(review -> reviewDTOs.add(modelMapper.map(review, ReviewDTO.class)));
        return reviewDTOs;
    }

    public ReviewDTO getById(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        return convertToDTO(review);
    }

    public ReviewDTO create(Review review, Long movie_id, Long critic_id) {
        Movie movie = movieRepository.findById(movie_id).orElse(null);
        Critic critic = criticRepository.findById(critic_id).orElse(null);
        review.setRating(5.0);
        review.setMovie(movie);
        review.setCritic(critic);
        reviewRepository.save(review);
        return convertToDTO(review);
    }

    public Review update(Long id, Review reviewDetails) {
        Review updateReview = reviewRepository.findById(id).orElse(null);
        updateReview.setRating(reviewDetails.getRating());
        updateReview.setMessage(reviewDetails.getMessage());
        updateReview.setDate(reviewDetails.getDate());
        return reviewRepository.save(updateReview);
    }

    public void delete(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        reviewRepository.delete(review);
    }

    public List<Review> getReviewsByMovie(Long movie_id) {
        Movie movie = movieRepository.findById(movie_id).orElse(null);
        return reviewRepository.findByMovie(movie);
    }

    public ReviewDTO convertToDTO(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }
}
