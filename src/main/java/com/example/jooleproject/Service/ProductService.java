package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.Product;

import java.util.List;

public interface ProductService {
    Product create(String manu, String ser, String model);
    String readAll();
    List<Product> list();
    List<Product> findByManufacturer(String manu);
    List<Product> findBySeries(String series);
    List<Product> findByModel(String model);
    Product findByID(Integer id);
    List<Product> findByAll(String manu, String series, String model);
    Product update(Product prod);
    Product updateAll(int id, String manu, String series, String model);
    void deleteByManufacturer(String manu);
}
