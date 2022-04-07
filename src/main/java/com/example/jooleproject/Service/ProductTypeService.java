package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.ProductType;

public interface ProductTypeService {
    ProductType create();
    String readAll();
    ProductType update(ProductType pt);
    void deleteByUseType(String useType);

}
