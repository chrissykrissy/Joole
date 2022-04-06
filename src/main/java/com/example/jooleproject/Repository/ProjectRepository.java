package com.example.jooleproject.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jooleproject.Entity.Project;
import java.util.List;
public interface ProjectRepository extends JpaRepository<Project,Integer>{
    List<Project>findByProjectId(Integer ProjectID);
}
