package com.study.jparemovetest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "DEPARTMENT")
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Team> teams = new ArrayList<>();

    private String name;

    public Department(String name) {
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

    public void setCompany(Company company) {
        this.company = company;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
        team.setDepartment(this);

    }
}
