package com.study.jparemovetest.repository;

import com.study.jparemovetest.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
