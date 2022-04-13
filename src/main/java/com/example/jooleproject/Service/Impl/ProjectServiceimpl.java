package com.example.jooleproject.Service.Impl;
import com.example.jooleproject.Entity.User;
import com.example.jooleproject.Repository.ProjectRepository;
import com.example.jooleproject.Entity.Project;
import com.example.jooleproject.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceimpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    @Transactional
    public Project Create( String projectName) {
        Project project = new Project(projectName);
        projectRepository.save(project);
        return project;
    }

    public Boolean create(Project project, User user) {
        project.setUser(user);
        projectRepository.save(project);
        return true;
    }


    public String Read() {
        StringBuilder sb = new StringBuilder();
        List<Project> list = projectRepository.findAll();
        for(Project p: list){
            sb.append(p.toString());
        }
        return sb.toString();
    }

    public Project Get(Integer Id) {
        Optional<Project> list = projectRepository.findById(Id);
        Project project = list.get();
        return project;
    }


    public Project Update(Integer id, String updatename) {
        Project project = projectRepository.getById(id);
        project.setProjectName(updatename);
        return project;
    }


    public void Delete(Integer id) {
        projectRepository.deleteById(id);


    }

}
