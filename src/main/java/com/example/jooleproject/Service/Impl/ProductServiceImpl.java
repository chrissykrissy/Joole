package com.example.jooleproject.Service.Impl;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Entity.TechnicalDetail;
import com.example.jooleproject.Repository.ProductRepository;
import com.example.jooleproject.Service.ProductService;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional
    public Product create(String manu, String ser, String model){
        Product prod = new Product(manu, ser, model);
        repository.save(prod);
        return prod;
    }

    public String readAll() {
        StringBuilder sb = new StringBuilder();
        List<Product> list = repository.findAll();
        for (Product p : list) {
            sb.append(p.toString());
        }
        return sb.toString();
    }

    public List<Product> list(){
        List<Product> list = repository.findAll();
        return list;
    }

    public List<Product> findByManufacturer(String manu){
        return repository.findByManufacturer(manu);
    }

    public List<Product> findBySeries(String series){
        return repository.findBySeries(series);
    }

    public List<Product> findByModel(String model){
        return repository.findByModel(model);
    }


    public List<Product> findByAll(String manu, String series, String model){
        return repository.findByManufacturerAndSeriesAndModel(manu, series, model);
    }

    public Product findByID(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Product update(Product prod){
        prod.setManufacturer("manu");
        prod.setSeries("series");
        prod.setModel("model");
        return prod;
    }

    public Product updateAll(int id, String manu, String ser, String model){
        Product toUpdate = repository.findById(id).get();
        toUpdate.setManufacturer(manu);
        toUpdate.setSeries(ser);
        toUpdate.setModel(model);
        repository.save(toUpdate);
        return toUpdate;
    }

    public List<Product> findByTechnicalDetail(TechnicalDetail td){
        return repository.findByTechnicalDetail(td).orElse(null);
    }



    public void deleteByManufacturer(String manu){
        List<Product> list = repository.findByManufacturer(manu);
        for (Product p : list) {
            repository.delete(p);
        }
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Product p, ProductType pt, TechnicalDetail td) {
        p.setProductType(pt);
        p.setTechnicalDetail(td);
        repository.save(p);
    }

    @Override
    public void save(Product p) {
        repository.save(p);
    }
}
