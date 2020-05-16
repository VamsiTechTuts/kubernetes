package com.example.k8s.springbootmongo.springbootk8smongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.k8s.springbootmongo.springbootk8smongo.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Long>{

}
