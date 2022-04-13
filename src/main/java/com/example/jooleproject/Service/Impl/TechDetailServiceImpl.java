package com.example.jooleproject.Service.Impl;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.TechnicalDetail;
import com.example.jooleproject.Repository.TechnicalDetailRepository;
import com.example.jooleproject.Service.ProductService;
import com.example.jooleproject.Service.TechDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechDetailServiceImpl implements TechDetailService {

    @Autowired
    TechnicalDetailRepository repo;

    @Transactional
    public TechnicalDetail create(){
        TechnicalDetail tech = new TechnicalDetail(5000,65,35,60,300);
        repo.save(tech);
        return repo.findByAirflowAfter(4000).get(0);
    }

    @Transactional
    public TechnicalDetail create(Product p, int airflow, int maxPower, int soundMax, int diameter, int height){
        TechnicalDetail tech = new TechnicalDetail(airflow, maxPower, soundMax, diameter, height);
        tech.setProduct(p);
        repo.save(tech);
        return tech;
    }

    public String readAll(){
        StringBuilder sb = new StringBuilder();
        List<TechnicalDetail> list = repo.findAll();
        for (TechnicalDetail t : list){
            sb.append(t.toString());
        }
        return sb.toString();
    }

    public List<Product> findByHeight(int h){
        List<TechnicalDetail> list = repo.findByHeightAfter(h);
        List<Product> retList = new ArrayList<>();
        for (TechnicalDetail td : list){
            retList.add(td.getProduct());
        }
        return retList;
    }

    public List<TechnicalDetail> findByAll(int airflow, int maxPower, int soundMax, int diameter, int height){
        List<TechnicalDetail> list = repo.findByAirflowAndMaxPowerAndSoundMaxAndDiameterAndHeight(airflow, maxPower, soundMax, diameter, height);
//        List<Product> retList = new ArrayList<>();
//        for (TechnicalDetail td : list){
//            retList.add(td.getProduct());
//        }
        return list;
    }



    @Override
    public TechnicalDetail findByID(Integer id) {
        return repo.findById(id).get();
    }

    public TechnicalDetail update(TechnicalDetail td){
        td.setAirflow(3000);
        td.setMaxPower(30);
        td.setSoundMax(2);
        return td;
    }

    public TechnicalDetail updateAll(int id, int airflow, int maxPower, int soundMax, int diameter, int height){
        TechnicalDetail toUpdate = repo.getById(id);
        toUpdate.setAirflow(airflow);
        toUpdate.setMaxPower(maxPower);
        toUpdate.setSoundMax(soundMax);
        toUpdate.setDiameter(diameter);
        toUpdate.setHeight(height);
        return toUpdate;
    }

    @Override
    public List<TechnicalDetail> findByAirflowRange(int airStart, int airEnd) {
//        List<TechnicalDetail> list =
                return repo.findByAirflowBetween(airStart,airEnd);
//        List<Product> retList = new ArrayList<>();
//        for (TechnicalDetail td : list){
//            retList.add(td.getProduct());
//        }
//        return retList;
    }

    @Override
    public List<TechnicalDetail> findByPowerRange(int powerStart, int powerEnd) {
        List<TechnicalDetail> list = repo.findByMaxPowerBetween(powerStart,powerEnd);
//        List<Product> retList = new ArrayList<>();
//        for (TechnicalDetail td : list){
//            retList.add(td.getProduct());
//        }
        return list;
    }

    @Override
    public List<TechnicalDetail> findBySoundRange(int soundStart, int soundEnd) {
        List<TechnicalDetail> list = repo.findBySoundMaxBetween(soundStart,soundEnd);
//        List<Product> retList = new ArrayList<>();
//        for (TechnicalDetail td : list){
//            retList.add(td.getProduct());
//        }
        return list;
    }

    @Override
    public List<TechnicalDetail> findByDiaRange(int diaStart, int diaEnd) {
        List<TechnicalDetail> list = repo.findByDiameterBetween(diaStart,diaEnd);
//        List<Product> retList = new ArrayList<>();
//        for (TechnicalDetail td : list){
//            retList.add(td.getProduct());
//        }
        return list;
    }

    @Override
    public List<TechnicalDetail> findByHeightRange(int heightStart, int heightEnd) {
        List<TechnicalDetail> list = repo.findByHeightBetween(heightStart,heightEnd);
//        List<Product> retList = new ArrayList<>();
//        for (TechnicalDetail td : list){
//            retList.add(td.getProduct());
//        }
        return list;
    }

    @Override
    public List<Product> findByRange(int airStart, int airEnd, int powerStart, int powerEnd, int soundStart, int soundEnd, int diaStart, int diaEnd, int heightStart, int heightEnd) {
        List<TechnicalDetail> list = repo.findByAirflowBetweenAndMaxPowerBetweenAndSoundMaxBetweenAndDiameterBetweenAndHeightBetween(airStart, airEnd, powerStart, powerEnd, soundStart, soundEnd, diaStart, diaEnd, heightStart, heightEnd);
        List<Product> retList = new ArrayList<>();
        for (TechnicalDetail td : list){
            retList.add(td.getProduct());
        }
        return retList;
    }

    public void deleteByHeight(int h){
        List<TechnicalDetail> list = repo.findByHeightAfter(h);
        for (TechnicalDetail td : list){
            repo.delete(td);
        }
    }

    @Override
    public void save(TechnicalDetail td) {
        repo.save(td);
    }

}
