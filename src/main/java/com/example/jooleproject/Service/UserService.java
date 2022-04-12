package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User Create(String username, String role, String password);

    String Read();

    User Update(User user);

    void Delete(String username);

    List<User> list();


}
