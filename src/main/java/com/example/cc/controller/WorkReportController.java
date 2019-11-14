package com.example.cc.controller;

import com.example.cc.dto.WorkReportDto;
import com.example.cc.exception.CreatedEntityIdException;
import com.example.cc.exception.UpdatedEntityIdException;
import com.example.cc.mapper.WorkReportMapper;
import com.example.cc.service.WorkReportService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workreport")
public class WorkReportController {

    private final WorkReportMapper workReportMapper;
    private final WorkReportService workReportService;

    public WorkReportController(WorkReportMapper workReportMapper, WorkReportService workReportService) {
        this.workReportMapper = workReportMapper;
        this.workReportService = workReportService;
    }

    @GetMapping
    public List<WorkReportDto> getAll() {
        return workReportMapper.toDtos(workReportService.getAll());
    }

    @GetMapping("{id}")
    public WorkReportDto getProduct(@PathVariable Long id) {
        return workReportMapper.toDto(workReportService.getWorkReport(id));
    }

    @PostMapping
    public void saveProduct(@RequestBody WorkReportDto workReportDto) throws CreatedEntityIdException {
        if (workReportDto.getId() != null) {
            throw new CreatedEntityIdException();
        }
        workReportService.saveWorkReport(workReportMapper.toEntity(workReportDto));
    }

    @PutMapping
    public void updateProduct(@RequestBody WorkReportDto workReportDto) throws UpdatedEntityIdException {
        if (workReportDto.getId() == null) {
            throw new UpdatedEntityIdException();
        }
        workReportService.saveWorkReport(workReportMapper.toEntity(workReportDto));
    }
}
