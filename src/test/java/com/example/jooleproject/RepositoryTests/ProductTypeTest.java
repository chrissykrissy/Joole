package com.example.jooleproject.RepositoryTests;

import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Repository.ProductRepository;
import com.example.jooleproject.Repository.ProductTypeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTypeTest {

    @Autowired
    ProductTypeRepository productTypeRepository;

//    @Autowired
//    ProductRepository productRepository;


    @Test
    public void saveTest(){
        ProductType productType = new ProductType("Commercial", "Indoor", "Roof", "With light", 2010);
//        productType.setProduct(productRepository.findById("3"));
        ProductType result = productTypeRepository.save(productType);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByUseType(){
        List<ProductType> result = productTypeRepository.findByUseType("Commercial");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByApplication(){
        List<ProductType> result = productTypeRepository.findByApplication("Indoor");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByMountingLocation(){
        List<ProductType> result = productTypeRepository.findByMountingLocation("Roof");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByAccessories(){
        List<ProductType> result = productTypeRepository.findByAccessories("With light");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByYearAfter(){
        List<ProductType> result = productTypeRepository.findByYearAfter(2009);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByYearBefore(){
        List<ProductType> result = productTypeRepository.findByYearBefore(2011);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByYearBetween(){
        List<ProductType> result = productTypeRepository.findByYearBetween(2009,2011);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

}
