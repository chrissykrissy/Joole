package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;

import java.util.List;

public interface ProductTypeService {
    ProductType create(Product p, String useType, String application, String mountingLocation, String accessories, int year);
    String readAll();
    List<Product> findByUseType(String useType);
    List<ProductType> findByAll(String useType, String application, String mountingLocation, String accessories, int year);
    ProductType findByID(Integer id);
    ProductType update(ProductType pt);
    ProductType updateAll(int id, String useType, String application, String mountingLocation, String accessories, int year);
    void deleteByUseType(String useType);
    void save(ProductType pt);

}
