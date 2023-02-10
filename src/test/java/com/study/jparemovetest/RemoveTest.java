package com.study.jparemovetest;

import com.study.jparemovetest.domain.Company;
import com.study.jparemovetest.domain.Department;
import com.study.jparemovetest.domain.Team;
import com.study.jparemovetest.repository.CompanyRepository;
import com.study.jparemovetest.repository.DepartmentRepository;
import com.study.jparemovetest.repository.TeamRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RemoveTest {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    TeamRepository teamRepository;

    @DisplayName("저장 테스트")
    @Test
    public void saveTest() {
        // given
        Company company = companyRepository.save(new Company("c1"));

        Department department1 = departmentRepository.save(new Department("d1"));
        Department department2 = departmentRepository.save(new Department("d2"));

        company.addDepartment(department1);
        company.addDepartment(department2);

        Team team11 = teamRepository.save(new Team("t11"));
        Team team12 = teamRepository.save(new Team("t12"));
        Team team21 = teamRepository.save(new Team("t21"));
        Team team22 = teamRepository.save(new Team("t22"));
        department1.addTeam(team11);
        department1.addTeam(team12);
        department2.addTeam(team21);
        department2.addTeam(team22);


        // when


        // then
        List<Team> teams = teamRepository.findAll();
        List<Department> members = departmentRepository.findAll();

        assertThat(members).hasSize(2);
        assertThat(teams).hasSize(4);
    }

    @DisplayName("orphanRemoval - department 삭제")
    @Test
    public void orphanRemoval_removeDepartment() {
        // given
        Company company = companyRepository.save(new Company("c1"));

        Department department1 = departmentRepository.save(new Department("d1"));
        Department department2 = departmentRepository.save(new Department("d2"));

        company.addDepartment(department1);
        company.addDepartment(department2);

        Team team11 = teamRepository.save(new Team("t11"));
        Team team12 = teamRepository.save(new Team("t12"));
        Team team21 = teamRepository.save(new Team("t21"));
        Team team22 = teamRepository.save(new Team("t22"));
        department1.addTeam(team11);
        department1.addTeam(team12);
        department2.addTeam(team21);
        department2.addTeam(team22);


        // when
        company.removeDepartment(department1);
        departmentRepository.delete(department1);

        // then
        List<Department> departments = departmentRepository.findAll();
        List<Team> teams = teamRepository.findAll();

        assertThat(company.departments()).hasSize(1);
        assertThat(departments).hasSize(1);
        assertThat(teams).hasSize(2);
    }
}
