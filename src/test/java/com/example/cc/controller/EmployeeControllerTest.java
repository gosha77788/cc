package com.example.cc.controller;

import com.example.cc.model.Employee;
import com.example.cc.service.EmployeeService;
import com.example.cc.service.dto.EmployeeDto;
import com.example.cc.service.mapper.EmployeeMapper;
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
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeMapper employeeMapper;

    @MockBean
    private EmployeeService employeeService;

    private final String URL = "/employee";

    private EmployeeDto buildEmployeeDto(Long id) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        employeeDto.setFullName("Yura");
        return employeeDto;
    }

    @Test
    void testGetAllEmployee() throws Exception {
        EmployeeDto employeeDto = buildEmployeeDto(1L);

        List<EmployeeDto> employeedtos = Arrays.asList(employeeDto);

        doReturn(new ArrayList<>()).when(employeeService).getAll();
        doReturn(employeedtos).when(employeeMapper).toDtos(any());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value(employeeDto.getFullName()));
    }

    @Test
    void testGetEmployee() throws Exception {
        EmployeeDto employeeDto = buildEmployeeDto(1L);

        doReturn(new Employee()).when(employeeService).getEmployee(anyLong());
        doReturn(employeeDto).when(employeeMapper).toDto(any(Employee.class));

        mockMvc.perform(get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(employeeDto.getFullName()));
    }

    @Test
    void testSaveEmployee() throws Exception {
        EmployeeDto employeeDto = buildEmployeeDto(null);

        doNothing().when(employeeService).saveEmployee(any(Employee.class));
        doReturn(new Employee()).when(employeeMapper).toEntity(employeeDto);

        assertNull(employeeDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    void TestUpdateEmployee() throws Exception {
        EmployeeDto employeeDto = buildEmployeeDto(1L);

        doNothing().when(employeeService).saveEmployee(any(Employee.class));
        doReturn(new Employee()).when(employeeMapper).toEntity(employeeDto);

        assertNotNull(employeeDto.getId());

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }
}
