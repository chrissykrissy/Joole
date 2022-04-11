package com.example.jooleproject.Repository;

import com.example.jooleproject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Integer> {

//    Product findByProduct_id(Integer id);

    List<Product> findByManufacturer(String manu);

    List<Product> findBySeries(String series);

    List<Product> findByModel(String model);

    List<Product> findByManufacturerAndAndSeriesAndModel(String manu, String Series, String Model);

}
