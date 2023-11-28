package com.example.project_zerowaste.Controllers;

import com.example.project_zerowaste.Configuration.GlobalException;
import com.example.project_zerowaste.Services.PackageService;
import com.example.project_zerowaste.Services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import com.example.project_zerowaste.Entities.Package;

@Controller
@RequestMapping("/packages")
@AllArgsConstructor

public class PackageController {
    private PackageService packageService;
    private ProductService productService;

    @GetMapping("/all")
    public String getAll(
            Model model,
            Principal principal
    ) {
        List<Package> packages = packageService.findAll(principal.getName());

        model.addAttribute("packages", packages);
        return "packages";
    }

    @GetMapping("/add")
    public String addPackageForm(Model model, Principal principal) {
        model.addAttribute("package", new Package());
        model.addAttribute("products", productService.findAll(principal.getName()));
        return "package-form";
    }

    @PostMapping("/add")
    public String addPackage(
            @ModelAttribute ("package") @Valid Package pack,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "package-form";
        }
        else {
            try {
                packageService.save(pack, principal.getName());
            } catch (GlobalException exception) {
                model.addAttribute("errorMessage", exception.getMessage());
                return "package-form";
            }
            return "redirect:/packages";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPackageForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Package pack = packageService.findById(id);
        model.addAttribute("package", pack);
        model.addAttribute("products", productService.findAll(principal.getName()));
        return "edit-package-form";
    }

    @PostMapping("/{id}/edit")
    public String editPackage(
            @PathVariable("id") Long id,
            @ModelAttribute("package") @Valid Package updatedPackage,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "edit-package-form";
        }
        else {
            packageService.editPackage(id, updatedPackage);
            return "redirect:/packages";
        }
    }

    @GetMapping("/{id}/delete")
    public String deletePackage(@PathVariable("id") Long id) {
        packageService.deletePackageById(id);
        return "redirect:/packages";
    }
}




