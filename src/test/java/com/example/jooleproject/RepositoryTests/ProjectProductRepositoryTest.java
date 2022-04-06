package com.example.jooleproject.RepositoryTests;
import com.example.jooleproject.Entity.ProjectProduct;
import com.example.jooleproject.Repository.ProjectProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectProductRepositoryTest {

    @Autowired
    ProjectProductRepository projectProductRepository;

    @Test
    public void saveTest(){
        ProjectProduct projectProduct = new ProjectProduct();
        projectProduct.setPrId(2);
    }
}
