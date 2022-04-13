package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.TechnicalDetail;

import java.util.List;

public interface TechDetailService {
    TechnicalDetail create(Product p, int airflow, int maxPower, int soundMax, int diameter, int height);
    TechnicalDetail create();
    String readAll();
    List<Product> findByHeight(int height);
    List<TechnicalDetail> findByAll(int airflow, int maxPower, int soundMax, int diameter, int height);
    TechnicalDetail findByID(Integer id);
    TechnicalDetail update(TechnicalDetail td);
    TechnicalDetail updateAll(int id, int airflow, int maxPower, int soundMax, int diameter, int height);

    List<TechnicalDetail> findByAirflowRange(int airStart, int airEnd);
    List<TechnicalDetail> findByPowerRange(int powerStart, int powerEnd);
    List<TechnicalDetail> findBySoundRange(int soundStart, int soundEnd);
    List<TechnicalDetail> findByDiaRange(int diaStart, int diaEnd);
    List<TechnicalDetail> findByHeightRange(int heightStart, int heightEnd);

    List<Product> findByRange(int airStart, int airEnd, int powerStart, int powerEnd, int soundStart, int soundEnd, int diaStart, int diaEnd, int heightStart, int heightEnd);


    //delete if greater than given height
    void deleteByHeight(int h);

    void save(TechnicalDetail td);
}
