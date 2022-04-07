package com.example.jooleproject.Service.Impl;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Repository.ProductRepository;
import com.example.jooleproject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional
    public Product create(){
        Product prod = new Product("Apple", "XS","Mini");
        repository.save(prod);
        return repository.findByManufacturer("Apple").get(0);
    }

    public String readAll(){
        StringBuilder sb = new StringBuilder();
        List<Product> list = repository.findAll();
        for (Product p : list){
           sb.append(p.toString());
        }
        return sb.toString();
    }

    public Product update(Product prod){
        prod.setManufacturer("manu");
        prod.setSeries("series");
        prod.setModel("model");
        return prod;
    }

    public void deleteByManufacturer(String manu){
        List<Product> list = repository.findByManufacturer(manu);
        for (Product p : list){
            repository.delete(p);
        }
    }




}
