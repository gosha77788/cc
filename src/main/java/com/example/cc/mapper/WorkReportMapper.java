package com.example.cc.mapper;

import com.example.cc.dto.WorkReportDto;
import com.example.cc.model.WorkReport;
import java.util.List;

public interface WorkReportMapper {

    WorkReportDto toDto(WorkReport workReport);

    WorkReport toEntity(WorkReportDto dto);

    List<WorkReportDto> toDtos(List<WorkReport> workReports);
}
