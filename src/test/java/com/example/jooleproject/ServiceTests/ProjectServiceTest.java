package com.example.jooleproject.ServiceTests;
import com.example.jooleproject.Service.Impl.ProjectServiceimpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.jooleproject.Entity.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    ProjectServiceimpl projectServiceimpl;

    @Test
    public void Create() throws Exception{
        Project result = projectServiceimpl.Create();
        Assert.assertNotNull(result);
    }

    @Test
    public void Read() throws Exception{
        String str = projectServiceimpl.Read();
        Assert.assertNotNull(str);
    }
    @Test
    public void Get() throws Exception{
        Project result = projectServiceimpl.Create();
        Project project = projectServiceimpl.Get(1);
    }

    @Test
    public void Update() throws Exception{
        Project before = projectServiceimpl.Create();
        Project after = projectServiceimpl.Update(before);
        Assert.assertEquals(before, after);

    }
    @Test
    public void Delete() throws Exception{
        projectServiceimpl.Delete(1);
        Assert.assertNotEquals(projectServiceimpl.Read(), "");
    }
}
