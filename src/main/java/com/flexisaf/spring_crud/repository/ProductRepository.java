package com.flexisaf.spring_crud.repository;

import com.flexisaf.spring_crud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    @Query("SELECT SUM(p.price) FROM Product p")
    Double getTotalSoldPrice();


    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThan(@Param("price") double price);
}
