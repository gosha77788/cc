package com.example.cc.controller;

import com.example.cc.service.dto.WorkReportDto;
import com.example.cc.service.mapper.WorkReportMapper;
import com.example.cc.model.WorkReport;
import com.example.cc.service.WorkReportService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WorkReportController.class)
class WorkReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkReportService workReportService;

    @MockBean
    private WorkReportMapper workReportMapper;

    private WorkReportDto buildWorkReportDto(Long id) {
        WorkReportDto workReportDto = new WorkReportDto();
        workReportDto.setId(id);
        workReportDto.setAmount(2L);
        return workReportDto;
    }

    private final String URL = "/workreport";

    @Test
    void testGetAll() throws Exception {
        WorkReportDto workReportDto = buildWorkReportDto(1L);

        List<WorkReportDto> workReportDtos = Arrays.asList(workReportDto);

        doReturn(new ArrayList<>()).when(workReportService).getAll();
        doReturn(workReportDtos).when(workReportMapper).toDtos(any());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount").value(workReportDto.getAmount()));
    }

    @Test
    void testGetWorkReport() throws Exception {
        WorkReportDto workReportDto = buildWorkReportDto(1L);

        doReturn(new WorkReport()).when(workReportService).getWorkReport(anyLong());
        doReturn(workReportDto).when(workReportMapper).toDto(any(WorkReport.class));

        mockMvc.perform(get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(workReportDto.getAmount()));
    }

    @Test
    void testSaveProduct() throws Exception {
        WorkReportDto workReportDto = buildWorkReportDto(null);

        doNothing().when(workReportService).saveWorkReport(any(WorkReport.class));
        doReturn(new WorkReport()).when(workReportMapper).toEntity(workReportDto);

        assertNull(workReportDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProduct() throws Exception {
        WorkReportDto workReportDto = buildWorkReportDto(1L);

        doNothing().when(workReportService).saveWorkReport(any(WorkReport.class));
        doReturn(new WorkReport()).when(workReportMapper).toEntity(workReportDto);

        assertNotNull(workReportDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }
}
