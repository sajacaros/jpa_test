package com.study.jparemovetest.repository;

import com.study.jparemovetest.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
