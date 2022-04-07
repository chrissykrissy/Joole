package com.example.jooleproject.Service.impl;
import com.example.jooleproject.*;
import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.User;
import com.example.jooleproject.Service.UserService;
import com.example.jooleproject.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Transactional
    public User Create() {
        User user = new User("bob@gmail.com", "customer","hello");
        userRepository.save(user);
        return user;
    }



    public String Read() {
        StringBuilder sb = new StringBuilder();
        List<User> list = userRepository.findAll();
        for(User p : list){
            sb.append(p.toString());
        }
        return sb.toString();
    }



    public User Update(User user) {
        user.setRole("Consumer");
        user.setPassword("hi");
        return user;
    }



    public void Delete(String username) {
        List<User> list= userRepository.findByUserId(username);
        for(User p: list){
            userRepository.delete(p);
        }

    }
}
