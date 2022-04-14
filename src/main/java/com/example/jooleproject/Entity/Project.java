package com.example.jooleproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer projectId;

    private String projectName;

    @CreatedDate
    private Timestamp timeCreated;

    @LastModifiedDate
    private Timestamp timeUpdated;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectProduct> projectProduct;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }
    public Timestamp getTimeUpdated() {
        return timeUpdated;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeUpdated(Timestamp timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public Project() {
    }


    public List<ProjectProduct> getProjectProduct() {
        return projectProduct;
    }

    public void setProjectProduct(List<ProjectProduct> projectProduct) {
        this.projectProduct = projectProduct;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", timeCreated=" + timeCreated +
                ", timeUpdated=" + timeUpdated +
                '}';
    }


    public Project(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
