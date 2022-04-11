package com.example.jooleproject.Service.Impl;

import com.example.jooleproject.Service.ProjectProductService;
import com.example.jooleproject.Entity.ProjectProduct;
import com.example.jooleproject.Repository.ProjectProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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
        return projectProductRepository.getById(Id);
    }


    public void Delete(Integer id) {
        projectProductRepository.deleteById(id);

    }
}
