package com.example.jooleproject;

import com.example.jooleproject.Entity.*;
import com.example.jooleproject.Repository.*;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD
<<<<<<< Updated upstream
import java.sql.Date;
=======
>>>>>>> Stashed changes
=======
>>>>>>> 2e6fad3ec57acf2c2deaf0138c889c9bb4bf2a43
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
<<<<<<< Updated upstream
    void full(){
=======
    void full() {
>>>>>>> Stashed changes

        //create user
        User user = new User("xsyjeon", "password", "customer");

        //create proj
        Project proj = new Project();

<<<<<<< Updated upstream
=======
        //set project user
        proj.setUser(user);

>>>>>>> Stashed changes
        //create list of ProjectProduct
        List<ProjectProduct> pjpr = new ArrayList<>();
        ProjectProduct projectProduct1 = new ProjectProduct();
        ProjectProduct projectProduct2 = new ProjectProduct();

        //create products
        Product prod = new Product("manu", "series", "model");
        Product prod2 = new Product("dior", "boutique", "grey");

<<<<<<< Updated upstream
        //set the proType/tech for each product
        ProductType ptype1 = new ProductType("Commercial", "Indoor", "Roof", "With light", 2010);
        prod.setProductType(ptype1);
        TechnicalDetail tech1 = new TechnicalDetail(100,200,300,400,500);
=======
        //set the proType/tech for each products
        ProductType ptype1 = new ProductType("Commercial", "Indoor", "Roof", "With light", 2010);
        prod.setProductType(ptype1);
        TechnicalDetail tech1 = new TechnicalDetail(100, 200, 300, 400, 500);
>>>>>>> Stashed changes
        prod.setTechnicalDetail(tech1);

        ProductType ptype2 = new ProductType("Industrial", "Outdoor", "Wall", "Without light", 2010);
        prod2.setProductType(ptype2);
<<<<<<< Updated upstream
        TechnicalDetail tech2 = new TechnicalDetail(1,2,3,4,5);
=======
        TechnicalDetail tech2 = new TechnicalDetail(1, 2, 3, 4, 5);
>>>>>>> Stashed changes
        prod2.setTechnicalDetail(tech2);

        //put the product as projectProduct
        projectProduct1.setProduct(prod);
<<<<<<< Updated upstream
        projectProduct1.setProject(proj);
        projectProduct2.setProduct(prod2);
        projectProduct2.setProject(proj);
=======
        projectProduct2.setProduct(prod2);
>>>>>>> Stashed changes
        pjpr.add(projectProduct1);
        pjpr.add(projectProduct2);
        proj.setProjectProduct(pjpr);

<<<<<<< Updated upstream
        //set project user
        proj.setUser(user);

=======
>>>>>>> Stashed changes
        //save all
        User savedUser = userRepository.save(user);

        Product savedProd1 = productRepository.save(prod);
        Product savedProd2 = productRepository.save(prod2);

        ProductType savedPT = productTypeRepository.save(ptype1);
        ProductType savedPT2 = productTypeRepository.save(ptype2);

        TechnicalDetail savedTD = technicalDetailRepository.save(tech1);
        TechnicalDetail savedTD2 = technicalDetailRepository.save(tech2);

        Project savedProject = projectRepository.save(proj);
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    }

}
