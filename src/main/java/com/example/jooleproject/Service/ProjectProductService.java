package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.ProjectProduct;

public interface ProjectProductService {

    ProjectProduct Create();

    String Read();

    ProjectProduct Updated(ProjectProduct projectProduct);

    void Delete(Integer id);

}
