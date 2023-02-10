package com.study.jparemovetest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity(name = "COMPANY")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Department> departments = new ArrayList<>();

    public Company(String name) {
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

    public void addDepartment(Department department) {
        this.departments.add(department);
        department.setCompany(this);
    }

    public List<Department> departments() {
        return Collections.unmodifiableList(this.departments);
    }

    public void removeDepartment(Department department) {
        this.departments.remove(department);
    }
}
