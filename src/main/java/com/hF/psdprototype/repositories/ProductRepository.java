package com.hF.psdprototype.repositories;

import com.hF.psdprototype.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByType(String type);

    List<Product> findByPrice(double price);


}
