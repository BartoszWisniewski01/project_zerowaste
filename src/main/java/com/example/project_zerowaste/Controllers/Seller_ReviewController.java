package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Configuration.GlobalException;
import com.example.project_zerowaste.Entities.*;
import com.example.project_zerowaste.Repositories.User_SellerRepository;
import com.example.project_zerowaste.Services.ProductService;
import com.example.project_zerowaste.Services.Seller_ReviewService;
import com.example.project_zerowaste.Services.UserService;
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
@RequestMapping("/seller-review")
public class Seller_ReviewController {
    private Seller_ReviewService sellerReviewService;
    private ProductService productService;
    private User_SellerRepository user_sellerRepository;
    private UserService userService;
    @GetMapping("/all")
    public String getAll(
            Model model,
            Principal principal
    ) {
        List<Seller_Review> sellerReviewList = sellerReviewService.findAll(principal.getName());

        model.addAttribute("seller_reviews", sellerReviewList);
        return "reviews-sellers";
    }

    @GetMapping("/{id}/add")
    public String addReviewForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Seller_Review sellerReview = new Seller_Review();
        sellerReview.setSeller(user_sellerRepository.findSellerByUser(userService.findById(id)));
        model.addAttribute("seller_review", sellerReview);
        model.addAttribute("seller_reviews", sellerReviewService.findAll(principal.getName()));
        return "review-seller-form";
    }

    @PostMapping("/add")
    public String addReview(
            @ModelAttribute ("product_review")
            @Valid Seller_Review sellerReview,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "review-seller-form";
        }
        else {
            try {
                sellerReviewService.save(sellerReview, principal.getName());
            } catch (GlobalException exception) {
                model.addAttribute("errorMessage", exception.getMessage());
                return "review-seller-form";
            }
            return "redirect:/seller-review/all";
        }
    }

    @GetMapping("/{id}/edit")
    public String editReviewForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Seller_Review sellerReview = sellerReviewService.findById(id);
        model.addAttribute("seller_review", sellerReview);
        model.addAttribute("seller_reviews", sellerReviewService.findAll(principal.getName()));
        return "edit-seller-review-form";
    }

    @PostMapping("/{id}/edit")
    public String editReview(
            @PathVariable("id") Long id,
            @ModelAttribute("seller_review") @Valid Seller_Review sellerReview,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "edit-seller-review-form";
        }
        else {
            sellerReviewService.editReview(id, sellerReview);
            return "redirect:/seller-review/all";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteReview(@PathVariable("id") Long id) {
        sellerReviewService.deleteReviewById(id);
        return "redirect:/seller-review/all";
    }
}