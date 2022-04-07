package com.example.jooleproject;

import com.example.jooleproject.Entity.*;
import com.example.jooleproject.Repository.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JooleProjectApplicationTests {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    ProjectProductRepository projectProductRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TechnicalDetailRepository technicalDetailRepository;
    @Autowired
    UserRepository userRepository;


    @Test

    void full() {


        //create user
        User user = new User("xsyjeon", "password", "customer");

        //create proj
        Project proj = new Project();
        //set project user
        proj.setUser(user);


        //create list of ProjectProduct
        List<ProjectProduct> pjpr = new ArrayList<>();
        ProjectProduct projectProduct1 = new ProjectProduct();
        ProjectProduct projectProduct2 = new ProjectProduct();

        //create products
        Product prod = new Product("manu", "series", "model");
        Product prod2 = new Product("dior", "boutique", "grey");


        //set the proType/tech for each product
        ProductType ptype1 = new ProductType("Commercial", "Indoor", "Roof", "With light", 2010);
        prod.setProductType(ptype1);
        TechnicalDetail tech1 = new TechnicalDetail(100,200,300,400,500);


        prod.setTechnicalDetail(tech1);

        ProductType ptype2 = new ProductType("Industrial", "Outdoor", "Wall", "Without light", 2010);
        prod2.setProductType(ptype2);

        TechnicalDetail tech2 = new TechnicalDetail(1,2,3,4,5);


        prod2.setTechnicalDetail(tech2);

        //put the product as projectProduct
        projectProduct1.setProduct(prod);

        projectProduct1.setProject(proj);
        projectProduct2.setProduct(prod2);
        projectProduct2.setProject(proj);

        projectProduct2.setProduct(prod2);

        pjpr.add(projectProduct1);
        pjpr.add(projectProduct2);
        proj.setProjectProduct(pjpr);


        //set project user
        proj.setUser(user);


        //save all
        User savedUser = userRepository.save(user);

        Product savedProd1 = productRepository.save(prod);
        Product savedProd2 = productRepository.save(prod2);

        ProductType savedPT = productTypeRepository.save(ptype1);
        ProductType savedPT2 = productTypeRepository.save(ptype2);

        TechnicalDetail savedTD = technicalDetailRepository.save(tech1);
        TechnicalDetail savedTD2 = technicalDetailRepository.save(tech2);

        Project savedProject = projectRepository.save(proj);




    }

}
