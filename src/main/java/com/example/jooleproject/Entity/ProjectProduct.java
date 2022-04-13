package com.example.jooleproject.Entity;

import com.example.jooleproject.Repository.ProjectRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class ProjectProduct {
    @Id
    @GeneratedValue

    private int prId;

    @CreatedDate
    private Timestamp timeCreated;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "project_id")

    private Project project;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrId() {
        return prId;
    }

    public void setPrId(int pr_Id) {
        this.prId = pr_Id;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public ProjectProduct() {
    }

    public ProjectProduct(int pr_Id, Timestamp timeCreated) {
        this.prId = pr_Id;
        this.timeCreated = timeCreated;
    }

    public ProjectProduct(Timestamp timeCreated) {
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
