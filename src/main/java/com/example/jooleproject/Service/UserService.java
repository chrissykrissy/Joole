package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.User;
import com.example.jooleproject.Enum.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User Create(String username, Role role, String password);

    String Read();

    User Get(Integer userId);

    User Update(User user);

    void Delete(Integer username);

    List<User> list();

    User saveUser(User user);

}
