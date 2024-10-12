package com.flexisaf.spring_crud.service;


import com.flexisaf.spring_crud.model.Product;
import com.flexisaf.spring_crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductByID(int id){
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public double getTotalAmountMade(){
        return productRepository.getTotalSoldPrice();
    }

    public List<Product> getProductsByPriceGreaterThan(double price) {
        return productRepository.findProductsByPriceGreaterThan(price);
    }

    public String deleteProduct(int id){
        productRepository.deleteById(id);

        return "Product deleted with id: " + id;
    }

    public Product updateProduct(Product product){
        Product exisitingProduct = productRepository.findById(product.getId()).orElse(null);
        exisitingProduct.setName(product.getName());
        exisitingProduct.setQuantity(product.getQuantity());
        exisitingProduct.setPrice(product.getPrice());

        return productRepository.save(exisitingProduct);
    }


}
