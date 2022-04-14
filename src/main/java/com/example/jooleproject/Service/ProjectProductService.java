package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.Project;
import com.example.jooleproject.Entity.ProjectProduct;

public interface ProjectProductService {

    ProjectProduct Create();
    Boolean create(ProjectProduct projectProduct, Project project, Product product);

    String Read();

    ProjectProduct get( Project project, Product product);

    ProjectProduct Get(Integer Id);

    void Delete(Integer id);

}
