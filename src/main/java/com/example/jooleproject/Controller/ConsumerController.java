package com.example.jooleproject.Controller;
import com.example.jooleproject.Repository.UserRepository;
import com.example.jooleproject.Service.Impl.*;
import com.example.jooleproject.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.ResponseEntity;
import java.security.Principal;

import java.time.Instant;

@RestController
@RequestMapping("/Consumer")
public class ConsumerController {
    @Autowired
    UserServiceimpl userServiceimpl;

    @Autowired
    ProjectServiceimpl projectServiceimpl;

    @Autowired
    ProjectProductServiceimpl projectProductServiceimpl;

    @Autowired
    ProductServiceImpl productServiceimpl;

    //http://localhost:8080/Consumer/addProject?userId=1&projectname=bobo
    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestParam(name = "userId") Integer userId, @RequestParam(name="projectname") String projectname){
        User user = userServiceimpl.Get(userId);
        Project projectToAdd = new Project(projectname);
        Boolean isSuccessful = projectServiceimpl.create(projectToAdd, user);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when creating new project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectToAdd, HttpStatus.OK);

    }
    //http://localhost:8080/Consumer/deleteProject?projectId=2
    @PostMapping("/deleteProject")
    public ResponseEntity<?> deleteProject( @RequestParam(name="projectId") Integer projectId){
        Project projectToDelete = projectServiceimpl.Get(projectId);
        projectServiceimpl.Delete(projectId);
        if(projectToDelete == null){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when deleting project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectToDelete, HttpStatus.OK);
    }
    //http://localhost:8080/Consumer/readProject?projectId=1
    @GetMapping("/readProject")
    public ResponseEntity<?> read(@RequestParam(name="projectId") Integer projectId) {
        Project project = projectServiceimpl.Get(projectId);
        if (project == null) {
            return new ResponseEntity<>("{\"error\":\"project not found!\"}", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(project, HttpStatus.OK);
        }

    }
    //http://localhost:8080/Consumer/updatename?projectId=1&projectname=read
    @PostMapping("/updatename")
    public ResponseEntity<?> updateName(@RequestParam(name = "projectId") Integer projectId, @RequestParam(name="projectname") String projectname)
    {
        Project project = projectServiceimpl.Get(projectId);
        project.setProjectName(projectname);

        if(project.getProjectName() != null){
            return new ResponseEntity<>(project, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when updating user!\"}",HttpStatus.BAD_REQUEST);
        }
    }


    //http://localhost:8080/Consumer/addProduct?productId=3&projectId=2
    @PostMapping("/addProduct")
    public ResponseEntity<?> addproduct(@RequestParam(name = "productId")Integer productId, @RequestParam(name = "projectId") Integer projectId){
        Project project = projectServiceimpl.Get(projectId);
        if (project == null) {
            return new ResponseEntity<>("{\"error\":\"project not found!\"}", HttpStatus.BAD_REQUEST);
        }
        Product product = productServiceimpl.findByID(productId);
        ProjectProduct ptp = new ProjectProduct();
        Boolean isSuccessful = projectProductServiceimpl.create(ptp, project, product);
        if(!isSuccessful)
        {
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when linking product to current project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ptp,HttpStatus.OK);
    }
    //http://localhost:8080/Consumer/deleteProduct?prId=6
    @PostMapping("/deleteProduct")
    public ResponseEntity<?> deleteproduct(@RequestParam(name = "prId")Integer prId)
    {
        ProjectProduct projectProduct = projectProductServiceimpl.Get(prId);
        projectProductServiceimpl.Delete(prId);
        if(projectProduct == null){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when deleting product from project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectProduct, HttpStatus.OK);
    }
}
