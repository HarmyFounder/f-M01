package com.hF.psdprototype.controllers;

import com.hF.psdprototype.models.Product;
import com.hF.psdprototype.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
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

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/all";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Product productToBeUpdated, @ModelAttribute("product") Product updatedProduct) {
        productService.update(productToBeUpdated, updatedProduct);
        return "redirect:/all";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Product product){
        productService.delete(product);
        return "redirect:/all";
    }

}
