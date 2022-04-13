package com.example.jooleproject.Service;
import com.example.jooleproject.Entity.Project;
import com.example.jooleproject.Entity.User;
import com.example.jooleproject.Repository.ProjectRepository;

public interface ProjectService {
    Project Create(String projectName);

    Boolean create(Project project, User user);

    String Read();

    Project Get(Integer Id);

    Project Update(Integer id, String updatename);

    void Delete(Integer id);

}