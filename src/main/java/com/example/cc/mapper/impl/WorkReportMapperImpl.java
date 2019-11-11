package com.example.cc.mapper.impl;

import com.example.cc.dto.WorkReportDto;
import com.example.cc.mapper.EmployeeMapper;
import com.example.cc.mapper.ProductMapper;
import com.example.cc.mapper.WorkReportMapper;
import com.example.cc.model.WorkReport;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WorkReportMapperImpl implements WorkReportMapper {

    private final EmployeeMapper employeeMapper;
    private final ProductMapper productMapper;

    public WorkReportMapperImpl(EmployeeMapper employeeMapper, ProductMapper productMapper) {
        this.employeeMapper = employeeMapper;
        this.productMapper = productMapper;
    }

    @Override
    public WorkReportDto toDto(WorkReport workReport) {
        WorkReportDto workReportDto = new WorkReportDto();
        workReportDto.setId(workReport.getId());
        workReportDto.setEmployee(employeeMapper.toDto(workReport.getEmployee()));
        workReportDto.setProduct(productMapper.toDto(workReport.getProduct()));
        workReportDto.setDoneAt(workReport.getDoneAt());
        workReportDto.setAmount(workReport.getAmount());
        return null;
    }

    @Override
    public WorkReport toEntity(WorkReportDto workReportDto) {
        WorkReport workReport = new WorkReport();
        workReport.setId(workReportDto.getId());
        workReport.setEmployee(employeeMapper.toEntity(workReportDto.getEmployee()));
        workReport.setProduct(productMapper.toEntity(workReportDto.getProduct()));
        workReport.setDoneAt(workReportDto.getDoneAt());
        workReport.setAmount(workReportDto.getAmount());
        return workReport;
    }

    @Override
    public List<WorkReportDto> toDtos(List<WorkReport> workReports) {
        return workReports.stream().map(this::toDto).collect(Collectors.toList());
    }
}
