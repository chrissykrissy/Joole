package com.example.jooleproject.Repository;

import com.example.jooleproject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByManufacturer(String manu);

    List<Product> findBySeries(String series);

    List<Product> findByModel(String model);

}
