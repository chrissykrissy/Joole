package com.example.jooleproject.Repository;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.TechnicalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findByManufacturer(String manu);

    List<Product> findBySeries(String series);

    List<Product> findByModel(String model);

    List<Product> findByManufacturerAndSeriesAndModel(String manu, String Series, String Model);

    Optional<List<Product>> findByTechnicalDetail(TechnicalDetail td);

}
