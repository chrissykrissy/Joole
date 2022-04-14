package com.example.jooleproject.RepositoryTests;
import com.example.jooleproject.Enum.Role;
import com.example.jooleproject.Repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.jooleproject.Entity.User;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("hiello");
        user.setPassword("hi");
        user.setRole(Role.BUYER);

        User result = userRepository.save(user);
        Assert.assertNotEquals(null,result);


    }
    @Test
    public void findByID(){
        List<User> result =userRepository.findByUserId(2);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }
}
