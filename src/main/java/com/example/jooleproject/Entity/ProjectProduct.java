package com.example.jooleproject.Entity;

import com.example.jooleproject.Repository.ProjectRepository;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity

public class ProjectProduct {
    @Id
    private int prId;

    private java.sql.Date timeCreated;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "project_id")
    //@JoinColumn(name = "product_id")

    private Project project;

    public int getPrId() {
        return prId;
    }

    public void setPrId(int pr_Id) {
        this.prId = pr_Id;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public ProjectProduct() {
    }

    public ProjectProduct(int pr_Id, Date timeCreated) {
        this.prId = pr_Id;
        this.timeCreated = timeCreated;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "ProjectProduct{" +
                "pr_Id=" + prId +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
