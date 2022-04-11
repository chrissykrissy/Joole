package com.example.jooleproject.ServiceTests;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Service.Impl.ProductTypeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTypeServiceTest {

    @Autowired
    ProductTypeServiceImpl ptImpl;

    @Test
    public void create() throws Exception{
        ProductType result = ptImpl.create();
        Assert.assertNotNull(result);
    }

    @Test
    public void readAll() throws Exception{
        String str = ptImpl.readAll();
        Assert.assertNotNull(str);
    }

    @Test
    public void findByAll(){
        List<ProductType> list = ptImpl.findByAll("Commercial", "Indoor", "Roof","With light", 2010);
        Assert.assertNotNull(list);
    }

    @Test
    public void update() throws Exception{
        ProductType before = ptImpl.create();
        ProductType after = ptImpl.update(before);
        Assert.assertEquals(before, after);
    }

    @Test
    public void deleteByUseType() throws Exception{
        ptImpl.deleteByUseType("Residential");
        ptImpl.deleteByUseType("Commercial");
        Assert.assertEquals(ptImpl.readAll(),"");
    }
}
