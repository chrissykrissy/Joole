package com.example.jooleproject.Entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class User {

    @Id
//    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private String userId;

    private String role;

    private String password;

    private java.sql.Date timeCreated;

    private java.sql.Date timeUpdated;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = {CascadeType.REMOVE})
    private List<Project> project;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public User() {
    }

    public User(String userId, String role, String password) {
        this.userId = userId;
        this.role = role;
        this.password = password;
    }


    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", timeCreated=" + timeCreated +
                ", timeUpdated=" + timeUpdated +
                '}';
    }
}
