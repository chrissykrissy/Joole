package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.TechnicalDetail;

public interface TechDetailService {
    TechnicalDetail create();
    String readAll();
    TechnicalDetail update(TechnicalDetail td);

    //delete if greater than given height
    void deleteByHeight(int h);
}
