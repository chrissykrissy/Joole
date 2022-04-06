package com.example.jooleproject.RepositoryTests;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveTest(){
        Product product = new Product("manu", "series", "model");
        Product result = productRepository.save(product);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByManufacturer(){
        List<Product> result = productRepository.findByManufacturer("manu");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findBySeries(){
        List<Product> result = productRepository.findBySeries("series");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByModel(){
        List<Product> result = productRepository.findByModel("model");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

}
