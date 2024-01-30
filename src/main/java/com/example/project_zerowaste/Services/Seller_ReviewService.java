package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Seller_Review;
import com.example.project_zerowaste.Repositories.Seller_ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Seller_ReviewService {
    private final Seller_ReviewRepository sellerReviewRepository;
    private final UserService userService;

    public void save(Seller_Review sellerReview, String username) {
        sellerReview.setUser(userService.findByUsername(username));
        sellerReviewRepository.save(sellerReview);
    }

    public List<Seller_Review> findAll(String username) {
        return sellerReviewRepository.findAllByUserUsername(username);
    }

    public void deleteReviewById(Long id) {
        sellerReviewRepository.deleteById(id);
    }

    public Seller_Review findById(Long id){
        return sellerReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID: " + id));
    }

    public void editReview(Long id, Seller_Review updatedReview) {
        Seller_Review sellerReview = sellerReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID: " + id));

        sellerReview.setRating(updatedReview.getRating());
        sellerReview.setComment(updatedReview.getComment());

        sellerReviewRepository.save(sellerReview);
    }
}
