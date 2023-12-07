package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Configuration.GlobalException;
import com.example.project_zerowaste.Entities.Product;
import com.example.project_zerowaste.Services.ProductService;
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
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @GetMapping("/all")
    public String getAll(
            Model model,
            Principal principal
    ) {
        List<Product> products = productService.findAll(principal.getName());

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model, Principal principal) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.findAll(principal.getName()));
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(
            @ModelAttribute ("product") @Valid Product product,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "product-form";
        }
        else {
            try {
                productService.save(product, principal.getName());
            } catch (GlobalException exception) {
                model.addAttribute("errorMessage", exception.getMessage());
                return "product-form";
            }
            return "redirect:/products/all";
        }
    }

    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("products", productService.findAll(principal.getName()));
        return "edit-product-form";
    }

    @PostMapping("/{id}/edit")
    public String editProduct(
            @PathVariable("id") Long id,
            @ModelAttribute("product") @Valid Product updatedProduct,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "edit-product-form";
        }
        else {
            productService.editProduct(id, updatedProduct);
            return "redirect:/products/all";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products/all";
    }
}