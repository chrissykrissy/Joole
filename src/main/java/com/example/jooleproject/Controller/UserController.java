package com.example.jooleproject.Controller;
import com.example.jooleproject.Entity.*;
import com.example.jooleproject.Enum.Role;
import com.example.jooleproject.Service.Impl.ProductServiceImpl;
import com.example.jooleproject.Service.Impl.ProjectProductServiceimpl;
import com.example.jooleproject.Service.Impl.ProjectServiceimpl;
import com.example.jooleproject.Service.MyUserDetailsService;
import com.example.jooleproject.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private AuthenticationManager myauthenticaitonManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

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

    //sign in
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestParam(name="username") String username,
                                                       @RequestParam(name="password") String password)
    //@RequestBody User User)
            throws Exception {

        try {
            myauthenticaitonManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)//User.getUsername(), User.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);//User.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return  new ResponseEntity<>(jwt, HttpStatus.OK);
    }


    //http://localhost:8080/UserController/create?username=hi&role=customer&password=hello
    @PostMapping("/create")
    public ResponseEntity<?> addUser(@RequestParam(name="username") String username, @RequestParam(name="role") Role role, @RequestParam(name="password") String password){
        User newuser = userServiceimpl.Create(username, role, password );
        if(newuser != null)
        {
            return new ResponseEntity<>(userServiceimpl.saveUser(newuser), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when creating new user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
    //http://localhost:8080/UserController/delete?userId=1
    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam(name="userId") Integer userId)
    {
        userServiceimpl.Delete(userId);
        if(userServiceimpl.Get(userId) == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when deleting user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
    //http://localhost:8080/UserController/read?userId=1
    @GetMapping("/read")
    public ResponseEntity<?> readUser(@RequestParam(name="userId") Integer userId){
        User user = userServiceimpl.Get(userId);
        if (user == null) {
            return new ResponseEntity<>("{\"error\":\"user not found!\"}", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
    //http://localhost:8080/UserController/updateusername?userId=1&newusername=hello
    @PostMapping("/updateusername")
    public ResponseEntity<?> updateUserId(@RequestParam(name="userId") Integer userId, @RequestParam(name = "newusername") String newusername)
    {
        User user = userServiceimpl.Get(userId);
        user.setUsername(newusername);
        if(user.getUserId() != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when updating user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
    //http://localhost:8080/UserController/updatepassword?userId=1&newpassword=wonderful
    @PostMapping("/updatepassword")
    public ResponseEntity<?> updatePassword(@RequestParam(name="userId") Integer userId, @RequestParam(name = "newpassword") String newpassword){
        User user = userServiceimpl.Get(userId);
        user.setPassword(newpassword);
        if(user.getUserId() != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when updating user!\"}",HttpStatus.BAD_REQUEST);
        }
    }
}
