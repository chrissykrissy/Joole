package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.TechnicalDetail;

import java.util.List;

public interface TechDetailService {
    TechnicalDetail create(int airflow, int maxPower, int soundMax, int diameter, int height);
    TechnicalDetail create();
    String readAll();
    List<Product> findByHeight(int height);
    List<TechnicalDetail> findByAll(int airflow, int maxPower, int soundMax, int diameter, int height);
    TechnicalDetail findByID(Integer id);
    TechnicalDetail update(TechnicalDetail td);
    TechnicalDetail updateAll(int id, int airflow, int maxPower, int soundMax, int diameter, int height);

    //delete if greater than given height
    void deleteByHeight(int h);
}
