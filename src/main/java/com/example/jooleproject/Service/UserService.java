package com.example.jooleproject.Service;

import com.example.jooleproject.Entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User Create();

    String Read();

    User Update(User user);

    void Delete();


}
