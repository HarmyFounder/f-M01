package com.hF.psdprototype.services;

import com.hF.psdprototype.models.Product;
import com.hF.psdprototype.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> getByType(String type){
        return productRepository.findByType(type);
    }

    public List<Product> getByPrice(double price){
        return productRepository.findByPrice(price);
    }

    public void create(Product product){
        productRepository.save(product);
    }

    public void update(Product productToBeUpdated, Product updatedProduct){
        BeanUtils.copyProperties(updatedProduct, productToBeUpdated, "id");
        productRepository.save(productToBeUpdated);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }

}
