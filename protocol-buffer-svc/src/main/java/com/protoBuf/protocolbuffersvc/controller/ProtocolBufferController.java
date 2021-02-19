package com.protoBuf.protocolbuffersvc.controller;

import com.google.protobuf.ListValue;
import com.protoBuf.protocolbuffersvc.entity.EmployeeDetails;
import com.protoBuf.protocolbuffersvc.service.ProtocalPufferService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import protocol.EmployeeBookProtos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProtocolBufferController {

    ProtocalPufferService protocalPufferService = new ProtocalPufferService();

    private EmployeeBookProtos.Employee Response;

    @GetMapping(value = "/employeedata")
    public EmployeeBookProtos.Employees getEmployeeResponse() throws RestClientException {
        return protocalPufferService.getEmployeeDetails();
    }


    //        ************************** Protocol Buffer Response class with Employee Specific ID ***************************
    @GetMapping("/employeedata/{employeeId}")
    public EmployeeBookProtos.Employee getEmployeeResponse(@PathVariable("employeeId") Long employeeId) throws RestClientException {
        Optional<EmployeeDetails> employeeDetailsById;
        employeeDetailsById = protocalPufferService.getEmployeeDetailsBasedOnId(employeeId);
        Response = EmployeeBookProtos.Employee.newBuilder()
                .setEmployeeId(employeeDetailsById.get().getEmployeeId().intValue())
                .setName(employeeDetailsById.get().getName())
                .setPhoneNumber(employeeDetailsById.get().getPhoneNumber())
                .setDepartment(employeeDetailsById.get().getDepartment())
                .setSalary(employeeDetailsById.get().getSalary())
                .setUpdateDate(employeeDetailsById.get().getUpdateDate().toString())
                .setCreateDate(employeeDetailsById.get().getCreateDate().toString())
                .build();
        return Response;
    }

    //        ***************************** JSON Response REST API Response class with Employee Specific ID *************************
    @GetMapping("/employeedataNormal/{employeeId}")
    public Optional<EmployeeDetails> getEmployeeResponse1(@PathVariable("employeeId") Long employeeId) throws RestClientException {
        Optional<EmployeeDetails> employeeDetailsById = protocalPufferService.getEmployeeDetailsBasedOnId(employeeId);
        return employeeDetailsById;
    }

    @PostMapping("/employeedata/employee")
    public ResponseEntity<EmployeeDetails> putEmployeeDetails(@RequestBody EmployeeDetails employee) {
        try {
            EmployeeDetails employeeDetails = protocalPufferService.insertEmployeeDetails(employee);
            return new ResponseEntity<>(employeeDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}