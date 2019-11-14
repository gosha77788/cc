package com.example.cc.service;

import com.example.cc.model.WorkReport;
import com.example.cc.repository.WorkReportRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkReportService {

    private final WorkReportRepository workReportRepository;

    public WorkReportService(WorkReportRepository workReportRepository) {
        this.workReportRepository = workReportRepository;
    }

    public List<WorkReport> getAll() {
        return workReportRepository.findAll();
    }

    public WorkReport getWorkReport(Long id) {
        return workReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void saveWorkReport(WorkReport workReport) {
        workReportRepository.save(workReport);
    }
}
