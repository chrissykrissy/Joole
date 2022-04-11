package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.ProjectProduct;

public interface ProjectProductService {

    ProjectProduct Create();

    String Read();

    ProjectProduct Get(Integer Id);

    void Delete(Integer id);

}
