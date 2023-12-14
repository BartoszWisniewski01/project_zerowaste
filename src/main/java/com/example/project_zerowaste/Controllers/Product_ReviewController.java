package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Configuration.GlobalException;
import com.example.project_zerowaste.Entities.Product;
import com.example.project_zerowaste.Entities.Product_Review;
import com.example.project_zerowaste.Services.ProductService;
import com.example.project_zerowaste.Services.Product_ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/product-review")
public class Product_ReviewController {
    private Product_ReviewService productReviewService;
    private ProductService productService;
    @GetMapping("/all")
    public String getAll(
            Model model,
            Principal principal
    ) {
        List<Product_Review> productReviewList = productReviewService.findAll(principal.getName());

        model.addAttribute("product_reviews", productReviewList);
        return "reviews-products";
    }

    @GetMapping("/{id}/add")
    public String addReviewForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Product product = productService.findById(id);
        Product_Review productReview = new Product_Review();
        productReview.setProduct(product);
        model.addAttribute("product_review", productReview);
        model.addAttribute("product_reviews", productReviewService.findAll(principal.getName()));
        return "review-product-form";
    }

    @PostMapping("/add")
    public String addReview(
            @ModelAttribute ("product_review")
            @Valid Product_Review productReview,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "review-product-form";
        }
        else {
            try {
                productReviewService.save(productReview, principal.getName());
            } catch (GlobalException exception) {
                model.addAttribute("errorMessage", exception.getMessage());
                return "review-product-form";
            }
            return "redirect:/product-review/all";
        }
    }

    @GetMapping("/{id}/edit")
    public String editReviewForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Product_Review productReview = productReviewService.findById(id);
        model.addAttribute("product_review", productReview);
        model.addAttribute("product_reviews", productReviewService.findAll(principal.getName()));
        return "edit-product-review-form";
    }

    @PostMapping("/{id}/edit")
    public String editReview(
            @PathVariable("id") Long id,
            @ModelAttribute("product_review") @Valid Product_Review productReview,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "edit-product-review-form";
        }
        else {
            productReviewService.editReview(id, productReview);
            return "redirect:/product-review/all";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteReview(@PathVariable("id") Long id) {
        productReviewService.deleteReviewById(id);
        return "redirect:/product-review/all";
    }
}