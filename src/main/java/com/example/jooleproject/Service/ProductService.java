package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.Product;

public interface ProductService {
    Product create();
    String readAll();
    Product update(Product prod);
    void deleteByManufacturer(String manu);
}
