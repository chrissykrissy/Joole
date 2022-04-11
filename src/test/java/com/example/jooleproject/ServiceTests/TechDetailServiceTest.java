package com.example.jooleproject.ServiceTests;

import com.example.jooleproject.Entity.*;
import com.example.jooleproject.Service.Impl.TechDetailServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TechDetailServiceTest {

    @Autowired
    TechDetailServiceImpl techServiceImpl;

    @Test
    public void create() throws Exception{
        TechnicalDetail result = techServiceImpl.create();
        Assert.assertNotNull(result);
    }

    @Test
    public void readAll() throws Exception{
        String str = techServiceImpl.readAll();
        Assert.assertNotNull(str);
    }

    @Test
    public void update() throws Exception{
        TechnicalDetail before = techServiceImpl.create();
        TechnicalDetail after = techServiceImpl.update(before);
        Assert.assertEquals(before, after);
    }

    @Test
    public void deleteByHeight() throws Exception{
        techServiceImpl.deleteByHeight(200);
        Assert.assertEquals(techServiceImpl.readAll(),"");
    }


}
