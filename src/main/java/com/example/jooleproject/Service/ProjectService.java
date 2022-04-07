package com.example.jooleproject.Service;
import com.example.jooleproject.Entity.Project;
import com.example.jooleproject.Repository.ProjectRepository;

public interface ProjectService {
    Project Create();

    String Read();

    Project Update(Project project);

    void Delete(Integer id);
}
