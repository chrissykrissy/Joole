package com.example.jooleproject.ServiceTests;
import com.example.jooleproject.Service.impl.ProjectProductServiceimpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.jooleproject.Entity.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectProductServiceTest {
    @Autowired
    ProjectProductServiceimpl projectProductServiceimpl;

    @Test
    public void Create() throws Exception{
        ProjectProduct result = projectProductServiceimpl.Create();
        Assert.assertNotNull(result);
    }
    @Test
    public void Read() throws Exception{
        String str = projectProductServiceimpl.Read();
        Assert.assertNotNull(str);
    }
    @Test
    public void Update() throws Exception{
        ProjectProduct before = projectProductServiceimpl.Create();
        ProjectProduct after = projectProductServiceimpl.Updated(before);
        Assert.assertEquals(before, after);
    }

    @Test
    public void Delete() throws Exception{
        projectProductServiceimpl.Delete(1);
        Assert.assertNotEquals(projectProductServiceimpl.Read(),"");
    }
}
