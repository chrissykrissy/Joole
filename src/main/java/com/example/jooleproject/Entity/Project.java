package com.example.jooleproject.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Project {

    @Id
    private Integer projectId;

    private java.sql.Date timeCreated;

    private java.sql.Date timeUpdated;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectProduct> projectProduct;

    public Integer getProjectId() {
        return projectId;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public Project() {
    }

    public Project(Integer projectId, Date timeCreated, Date timeUpdated) {
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
