package com.protoBuf.protocolbuffersvc.controller;


import com.protoBuf.protocolbuffersvc.entity.EmployeeDetails;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import protocol.EmployeeBookProtos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class ProtocolBufferControllerTest {

    @Mock
    EmployeeDetails employeeDetails;

    @InjectMocks
    ProtocolBufferController protocolBufferController;

    Long employeeId = new Random().nextLong();

    @Test
    public void getEmployeeResponse1Test(){
        Mockito.when(protocolBufferController.getEmployeeResponse1(employeeId)).thenReturn(getEmployeeDetailsRecords());
        protocolBufferController.getEmployeeResponse1(employeeId);
    }

    @Test
    public void getEmployeeResponseTest()throws JSONException, IOException {
        String empName = "Mohit";
        String Department = "Roche";
        int Salary = new Random().nextInt();
        EmployeeBookProtos.Employee employee= EmployeeBookProtos.Employee.newBuilder()
                .setEmployeeId(employeeId.intValue())
                .setName(empName)
                .setDepartment(Department)
                .setSalary(Salary)
                .build();
        assertEquals(employee.getEmployeeId(), employeeId.intValue());
        assertEquals(employee.getName(), empName);
        assertEquals(employee.getDepartment(), Department);
        assertEquals(employee.getSalary(), Salary);
    }

    public Optional<EmployeeDetails> getEmployeeDetailsRecords(){
        EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployeeId(employeeId);
            employeeDetails.setName("Mohit");
            employeeDetails.setDepartment("Roche");
            employeeDetails.setPhoneNumber(anyString());
            employeeDetails.setUpdateDate(LocalDate.now());
            employeeDetails.setCreateDate(LocalDate.now());
        return Optional.of(employeeDetails);
    }
}
