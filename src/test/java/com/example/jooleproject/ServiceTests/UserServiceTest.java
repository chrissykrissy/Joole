package com.example.jooleproject.ServiceTests;
import com.example.jooleproject.Service.Impl.UserServiceimpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.jooleproject.Entity.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserServiceimpl userServiceimpl;

    @Test
    public void create() throws Exception{
        User result = userServiceimpl.Create();
        Assert.assertNotNull(result);
    }

    @Test
    public void Read() throws Exception{
        String str = userServiceimpl.Read();
        Assert.assertNotNull(str);
    }

    @Test
    public void Update(){
        User before = userServiceimpl.Create();
        User after = userServiceimpl.Update(before);
        Assert.assertEquals(before, after);
    }
    @Test
    public void Delete(){
        String username = "bob@gmail.com";
        userServiceimpl.Delete(username);
        Assert.assertEquals(userServiceimpl.Read(),"");
    }
}
