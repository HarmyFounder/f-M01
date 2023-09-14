package com.hF.psdprototype.controllers;

import com.hF.psdprototype.models.Product;
import com.hF.psdprototype.models.User;
import com.hF.psdprototype.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "all";
    }

    @GetMapping("/{id}")
    public String getCertain(@PathVariable("id") Product product, Model model) {
        model.addAttribute("product", product);
        return "certain";
    }

    @PostMapping("/new")
    public String create(@RequestParam String title, Model model, @AuthenticationPrincipal User user) {
        Product product = new Product(title);
        productService.create(user, product);
        model.addAttribute("products", productService.getAll());
        user.getProducts().add(product);
        System.out.println(user.getProducts());
        return "all";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Product productToBeUpdated, @ModelAttribute("product") Product updatedProduct) {
        productService.update(productToBeUpdated, updatedProduct);
        return "redirect:/products/all";
    }

    @PostMapping("/{id}")
    public String delete(@AuthenticationPrincipal User user,@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
        productService.delete(user, product);
        System.out.println(user.getProducts());
        return "redirect:/products/all";
    }

}
