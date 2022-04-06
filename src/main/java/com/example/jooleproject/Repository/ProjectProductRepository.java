package com.example.jooleproject.Repository;
import com.example.jooleproject.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.jooleproject.Entity.ProjectProduct;
public interface ProjectProductRepository extends JpaRepository<ProjectProduct,Integer>{
    List<ProjectProduct>findByPr_Id(Integer prID);
}
