package com.example.jooleproject.Service.impl;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.TechnicalDetail;
import com.example.jooleproject.Repository.TechnicalDetailRepository;
import com.example.jooleproject.Service.ProductService;
import com.example.jooleproject.Service.TechDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String readAll(){
        StringBuilder sb = new StringBuilder();
        List<TechnicalDetail> list = repo.findAll();
        for (TechnicalDetail t : list){
            sb.append(t.toString());
        }
        return sb.toString();
    }

    public TechnicalDetail update(TechnicalDetail td){
        td.setAirflow(3000);
        td.setMaxPower(30);
        td.setSoundMax(2);
        return td;
    }

    public void deleteByHeight(int h){
        List<TechnicalDetail> list = repo.findByHeightAfter(h);
        for (TechnicalDetail td : list){
            repo.delete(td);
        }
    }

}
