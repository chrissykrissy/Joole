package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User Create(User user);

    User Read(User user);

    User Update(User user);

    User Delete(User user);


}
