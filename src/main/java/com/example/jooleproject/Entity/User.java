package com.example.jooleproject.Entity;


import com.example.jooleproject.Enum.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer userId;

    private String username;

    @Enumerated(EnumType.STRING)
    @Column (name = "role")
    private Role role;

    private String password;

    @CreatedDate
    private Date timeCreated;

    @LastModifiedDate
    private Date timeUpdated;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = {CascadeType.REMOVE})
    private List<Project> project;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

    public User() {
    }

    public User(String username, Role role, String password) {
        this.username = username;
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
