package com.example.jooleproject.Service.Impl;
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
    public Project Create() {
        Project project = new Project();
        projectRepository.save(project);
        return project;
    }


    public String Read() {
        StringBuilder sb = new StringBuilder();
        List<Project> list = projectRepository.findAll();
        for(Project p: list){
            sb.append(p.toString());
        }
        return sb.toString();
    }


    public Project Update(Project project) {
        Timestamp instant = Timestamp.from(Instant.now());
        project.setTimeUpdated(instant);
        return project;
    }


    public void Delete(Integer id) {
        projectRepository.deleteById(id);


    }
}
