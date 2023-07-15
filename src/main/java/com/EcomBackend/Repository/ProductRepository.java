package com.EcomBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EcomBackend.Model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
}
