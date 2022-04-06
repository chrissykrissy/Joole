package com.example.jooleproject.RepositoryTests;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.jooleproject.Entity.Project;
import com.example.jooleproject.Repository.ProjectRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void saveTest(){
        Project project = new Project();
        project.setProjectId(2);
        //project.setTimeCreated(Date.valueOf("04-06-2022"));

        Project result = projectRepository.save(project);
        Assert.assertNotEquals(null,result);

    }

}
