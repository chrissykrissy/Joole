package com.example.jooleproject.Service.Impl;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Repository.ProductTypeRepository;
import com.example.jooleproject.Service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public ProductType create(String useType, String application, String mountingLocation, String accessories, int year){
        ProductType pt = new ProductType(useType, application, mountingLocation, accessories, year);
        repo.save(pt);
        return pt;
    }

    public String readAll(){
        StringBuilder sb = new StringBuilder();
        List<ProductType> list = repo.findAll();
        for (ProductType p : list){
            sb.append(p.toString());
        }
        return sb.toString();
    }

    public List<Product> findByUseType(String useType){
        List<ProductType> list = repo.findByUseType(useType);
        List<Product> retList = new ArrayList<>();
        for (ProductType pt : list){
            retList.add(pt.getProduct());
        }
        return retList;
    }

    public List<ProductType> findByAll(String useType, String application, String mountingLocation, String accessories, int year){
        List<ProductType> list = repo.findByUseTypeAndApplicationAndMountingLocationAndAccessoriesAndYear(useType, application, mountingLocation, accessories, year);
//        List<Product> retList = new ArrayList<>();
//        for (ProductType pt : list){
//            retList.add(pt.getProduct());
//        }
        return list;
    }

    @Override
    public ProductType findByID(Integer id) {
        return repo.getById(id);
    }

    public ProductType update(ProductType pt){
        pt.setUseType("Residential");
        pt.setApplication("Outdoor");
        pt.setMountingLocation("Wall");
        pt.setAccessories("Without light");
        pt.setYear(2022);

        return pt;
    }

    public ProductType updateAll(int id, String useType, String application, String mountingLocation, String accessories, int year){
        ProductType pt = repo.findById(id).get();
        pt.setUseType(useType);
        pt.setApplication(application);
        pt.setMountingLocation(mountingLocation);
        pt.setAccessories(accessories);
        pt.setYear(year);
        return pt;
    }

    public void deleteByUseType(String useType){
        List<ProductType> list = repo.findByUseType(useType);
        for (ProductType p : list){
            repo.delete(p);
        }
    }

}
