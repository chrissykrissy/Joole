package com.example.jooleproject.Entity;

import com.example.jooleproject.Repository.ProjectRepository;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity

public class ProjectProduct {
    @Id
    private int pr_Id;

    private java.sql.Date timeCreated;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "project_id")
    private Project project;

    public int getPr_Id() {
        return pr_Id;
    }

    public void setPr_Id(int pr_Id) {
        this.pr_Id = pr_Id;
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
        this.pr_Id = pr_Id;
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
                "pr_Id=" + pr_Id +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
