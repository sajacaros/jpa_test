package com.study.jparemovetest.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TEAM")
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public void name(String s) {
        this.name = s;
    }

    public Long id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
