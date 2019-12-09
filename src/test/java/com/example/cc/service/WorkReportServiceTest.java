package com.example.cc.service;

import com.example.cc.model.WorkReport;
import com.example.cc.repository.WorkReportRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class WorkReportServiceTest {

    @InjectMocks
    private WorkReportService workReportService;

    @Mock
    private WorkReportRepository workReportRepository;

    private WorkReport buildWorkReport() {
        WorkReport workReport = new WorkReport();
        workReport.setId(1L);
        return workReport;
    }

    @Test
    void testGetAll() {
        WorkReport workReport = buildWorkReport();
        List<WorkReport> workReports = new ArrayList<>();
        workReports.add(workReport);

        when(workReportRepository.findAll()).thenReturn(workReports);
        workReportService.getAll();
        verify(workReportRepository, times(1)).findAll();
    }

    @Test
    void testGetWorkReport() {
        WorkReport workReport = buildWorkReport();

        when(workReportRepository.findById(workReport.getId())).thenReturn(Optional.of(workReport));
        WorkReport actualWorkReport = workReportService.getWorkReport(workReport.getId());
        assertEquals(workReport, actualWorkReport);
        verify(workReportRepository, times(1)).findById(workReport.getId());
    }

    @Test
    void testSaveWorkReport() {
        WorkReport workReport = buildWorkReport();

        when(workReportRepository.save(workReport)).thenReturn(null);
        workReportService.saveWorkReport(workReport);
        verify(workReportRepository, times(1)).save(workReport);
    }
}
