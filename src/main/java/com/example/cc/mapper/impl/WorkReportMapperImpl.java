package com.example.cc.mapper.impl;

import com.example.cc.dto.WorkReportDto;
import com.example.cc.mapper.EmployeeMapper;
import com.example.cc.mapper.ProductMapper;
import com.example.cc.mapper.WorkReportMapper;
import com.example.cc.model.WorkReport;
import com.example.cc.service.EmployeeService;
import com.example.cc.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WorkReportMapperImpl implements WorkReportMapper {

    private final EmployeeMapper employeeMapper;
    private final ProductMapper productMapper;
    private final EmployeeService employeeService;
    private final ProductService productService;


    public WorkReportMapperImpl(EmployeeMapper employeeMapper, ProductMapper productMapper, EmployeeService employeeService, ProductService productService) {
        this.employeeMapper = employeeMapper;
        this.productMapper = productMapper;
        this.employeeService = employeeService;
        this.productService = productService;
    }

    @Override
    public WorkReportDto toDto(WorkReport workReport) {
        WorkReportDto workReportDto = new WorkReportDto();
        workReportDto.setId(workReport.getId());
        workReportDto.setEmployee(employeeMapper.toDto(workReport.getEmployee()));
        workReportDto.setProduct(productMapper.toDto(workReport.getProduct()));
        workReportDto.setDoneAt(workReport.getDoneAt());
        workReportDto.setAmount(workReport.getAmount());
        return workReportDto;
    }

    @Override
    public WorkReport toEntity(WorkReportDto workReportDto) {
        WorkReport workReport = new WorkReport();
        workReport.setId(workReportDto.getId());
        workReport.setEmployee(employeeService.getEmployee(workReportDto.getEmployee().getId()));
        workReport.setProduct(productService.getProduct(workReportDto.getProduct().getId()));
        workReport.setDoneAt(workReportDto.getDoneAt());
        workReport.setAmount(workReportDto.getAmount());
        return workReport;
    }

    @Override
    public List<WorkReportDto> toDtos(List<WorkReport> workReports) {
        return workReports.stream().map(this::toDto).collect(Collectors.toList());
    }
}
