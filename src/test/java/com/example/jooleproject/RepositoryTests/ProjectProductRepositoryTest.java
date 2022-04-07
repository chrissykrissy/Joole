package com.example.jooleproject.RepositoryTests;
import com.example.jooleproject.Entity.ProjectProduct;
import com.example.jooleproject.Repository.ProjectProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectProductRepositoryTest {

    @Autowired
    ProjectProductRepository projectProductRepository;

    @Test
    public void saveTest(){
        ProjectProduct projectProduct = new ProjectProduct();
        Timestamp instant = Timestamp.from(Instant.now());
        projectProduct.setTimeCreated(instant);

        ProjectProduct result = projectProductRepository.save(projectProduct);
        Assert.assertNotEquals(null,result);
    }
}
