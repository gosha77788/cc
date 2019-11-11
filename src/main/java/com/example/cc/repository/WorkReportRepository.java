package com.example.cc.repository;

import com.example.cc.model.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {
}
