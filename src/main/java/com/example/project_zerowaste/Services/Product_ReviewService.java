package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.Product_Review;
import com.example.project_zerowaste.Repositories.Product_ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Product_ReviewService {
    private final Product_ReviewRepository productReviewRepository;
    private final UserService userService;

    public void save(Product_Review productReview, String username) {
        productReview.setUser(userService.findByUsername(username));
        productReviewRepository.save(productReview);
    }

    public List<Product_Review> findAll(String username) {
        return productReviewRepository.findAllByUserUsername(username);
    }

    public void deleteReviewById(Long id) {
        productReviewRepository.deleteById(id);
    }

    public Product_Review findById(Long id){
        return productReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID: " + id));
    }

    public void editReview(Long id, Product_Review updatedReview) {
        Product_Review productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID: " + id));

        productReview.setRating(updatedReview.getRating());
        productReview.setComment(updatedReview.getComment());

        productReviewRepository.save(productReview);
    }
}
