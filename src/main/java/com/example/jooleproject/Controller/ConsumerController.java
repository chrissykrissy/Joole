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

    @PostMapping("/Consumer/addProject")
    public ResponseEntity<?> addProject(){
        Project projectToAdd = new Project();
        boolean isSuccessful = projectServiceimpl.create(projectToAdd);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when creating new project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectToAdd, HttpStatus.OK);

    }
    @PostMapping("/Consumer/deleteProject")
    public ResponseEntity<?> deleteProject( @RequestParam(name="projectId") Integer projectId){
        Project projectToDelete = projectServiceimpl.Get(projectId);
        projectServiceimpl.Delete(projectId);
        if(projectToDelete == null){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when deleting project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectToDelete, HttpStatus.OK);
    }
    @GetMapping("/Consumer/readProject")
    public ResponseEntity<?> read(@RequestParam(name="projectId") Integer projectId) {
        Project project = projectServiceimpl.Get(projectId);
        if (project == null) {
            return new ResponseEntity<>("{\"error\":\"project not found!\"}", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(project, HttpStatus.OK);
        }

    }

    @PostMapping("/Consumer/productToProjectView")
    public ResponseEntity<?> view(@RequestParam(name = "prId")Integer prId){
        ProjectProduct projectProduct = projectProductServiceimpl.Get(prId);
        if (projectProduct == null) {
            return new ResponseEntity<>("{\"error\":\"project not found!\"}", HttpStatus.BAD_REQUEST);
        }
        String body = projectServiceimpl.Read();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
