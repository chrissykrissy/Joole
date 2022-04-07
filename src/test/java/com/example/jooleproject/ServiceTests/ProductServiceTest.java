package com.example.jooleproject.ServiceTests;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Service.Impl.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Test
    public void create() throws Exception{
        Product result = productServiceImpl.create();
        Assert.assertNotNull(result);
    }

    @Test
    public void readAll() throws Exception{
        String str = productServiceImpl.readAll();
        Assert.assertNotNull(str);
    }

    @Test
    public void update() throws Exception{
        Product before = productServiceImpl.create();
        Product after = productServiceImpl.update(before);
        Assert.assertEquals(before, after);
    }

    @Test
    public void deleteByManufacturer() throws Exception{
        productServiceImpl.deleteByManufacturer("manu");
        productServiceImpl.deleteByManufacturer("Apple");
        Assert.assertEquals(productServiceImpl.readAll(),"");
    }
}
