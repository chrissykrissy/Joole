package com.example.jooleproject.Service.impl;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Repository.ProductTypeRepository;
import com.example.jooleproject.Service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeRepository repo;

    @Transactional
    public ProductType create(){
        ProductType pt = new ProductType("Commercial", "Indoor", "Roof","With light", 2010);
        repo.save(pt);
        return repo.findByUseType("Commercial").get(0);
    }

    public String readAll(){
        StringBuilder sb = new StringBuilder();
        List<ProductType> list = repo.findAll();
        for (ProductType p : list){
            sb.append(p.toString());
        }
        return sb.toString();
    }

    public ProductType update(ProductType pt){
        pt.setUseType("Residential");
        pt.setApplication("Outdoor");
        pt.setMountingLocation("Wall");
        pt.setAccessories("Without light");
        pt.setYear(2022);

        return pt;
    }

    public void deleteByUseType(String useType){
        List<ProductType> list = repo.findByUseType(useType);
        for (ProductType p : list){
            repo.delete(p);
        }
    }

}
