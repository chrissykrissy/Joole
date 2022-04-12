package com.example.jooleproject.Controller;
import com.example.jooleproject.Entity.*;
import com.example.jooleproject.Service.Impl.ProductServiceImpl;
import com.example.jooleproject.Service.Impl.ProjectProductServiceimpl;
import com.example.jooleproject.Service.Impl.ProjectServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.ResponseEntity;
import com.example.jooleproject.Service.Impl.UserServiceimpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    UserServiceimpl userServiceimpl;

    @Autowired
    ProjectServiceimpl projectServiceimpl;

    @Autowired
    ProjectProductServiceimpl projectProductServiceimpl;

    @Autowired
    ProductServiceImpl productServiceimpl;

    //Get all products from database
    @GetMapping("/users")
    public ResponseEntity<?> list(){
        List<User> list = userServiceimpl.list();
        if(list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"User not found!\"}", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> addUser( @RequestParam(name="userId") String userId,@RequestParam(name="role") String role, @RequestParam(name="password") String password){
        User newuser = userServiceimpl.Create(userId, role, password );
        if(newuser != null)
        {
            return new ResponseEntity<>(newuser, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when creating new user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam(name="userId") String userId)
    {
        userServiceimpl.Delete(userId);
        if(userServiceimpl.Get(userId) == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when deleting user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/read")
    public ResponseEntity<?> readUser(@RequestParam(name="userId") String userId){
        User user = userServiceimpl.Get(userId);
        if (user == null) {
            return new ResponseEntity<>("{\"error\":\"user not found!\"}", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
    @PostMapping("/UserController/updateid")
    public ResponseEntity<?> updateUserId(@RequestParam(name="userId") String userId, @RequestParam(name = "newuserId") String newuserId)
    {
        User user = userServiceimpl.Get(userId);
        Timestamp instant = Timestamp.from(Instant.now());
        user.setUserId(newuserId);
        user.setTimeUpdated(instant);
        if(user.getUserId() != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when updating user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/UserController/updatepassword")
    public ResponseEntity<?> updatePassword(@RequestParam(name="userId") String userId, @RequestParam(name = "newpassword") String newpassword){
        User user = userServiceimpl.Get(userId);
        Timestamp instant = Timestamp.from(Instant.now());
        user.setPassword(newpassword);
        user.setTimeUpdated(instant);
        if(user.getUserId() != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when updating user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
}
