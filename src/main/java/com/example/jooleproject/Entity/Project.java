package com.example.jooleproject.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer projectId;

    private Timestamp timeCreated;

    private Timestamp timeUpdated;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
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


    public Project(Integer projectId, Timestamp timeCreated, Timestamp timeUpdated) {
        this.projectId = projectId;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
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


}
