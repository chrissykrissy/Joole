package com.example.jooleproject.Service.Impl;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.Project;
import com.example.jooleproject.Service.ProjectProductService;
import com.example.jooleproject.Entity.ProjectProduct;
import com.example.jooleproject.Repository.ProjectProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectProductServiceimpl implements ProjectProductService {

    @Autowired
    ProjectProductRepository projectProductRepository;


    @Transactional
    public ProjectProduct Create() {
        Timestamp instant = Timestamp.from(Instant.now());
        ProjectProduct projectProduct = new ProjectProduct(instant);
        projectProductRepository.save(projectProduct);
        return projectProduct;
    }

    public Boolean create(ProjectProduct projectProduct, Project project, Product product){
        projectProduct.setProject(project);
        projectProduct.setProduct(product);
        projectProductRepository.save(projectProduct);
        return true;

    }

    @Override
    public ProjectProduct get( Project project, Product product) {

        return null;
    }

    public String Read() {
        StringBuilder sb = new StringBuilder();
        List<ProjectProduct> list = projectProductRepository.findAll();
        for(ProjectProduct p : list)
        {
            sb.append(p.toString());
        }
        return sb.toString();
    }

    public ProjectProduct Get(Integer Id) {
        Optional<ProjectProduct> list = projectProductRepository.findById(Id);
        ProjectProduct projectProduct = list.get();
        return projectProduct;

    }


    public void Delete(Integer id) {
        projectProductRepository.deleteById(id);

    }
}
