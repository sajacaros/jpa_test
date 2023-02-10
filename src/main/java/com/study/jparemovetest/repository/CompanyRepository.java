package com.study.jparemovetest.repository;

import com.study.jparemovetest.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
