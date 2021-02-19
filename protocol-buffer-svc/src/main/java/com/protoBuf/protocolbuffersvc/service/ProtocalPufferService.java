package com.protoBuf.protocolbuffersvc.service;

import com.protoBuf.protocolbuffersvc.entity.EmployeeDetails;
import com.protoBuf.protocolbuffersvc.repository.ProtocalBufferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import protocol.EmployeeBookProtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProtocalPufferService {

    @Autowired
    private ProtocalBufferRepository protocalBufferRepository;

    List<EmployeeDetails> employeeDetails =  new ArrayList<>();
    Optional<EmployeeDetails> employeeDetailsByID;
    EmployeeDetails Response;

    public EmployeeBookProtos.Employees getEmployeeDetails(){
        List<EmployeeBookProtos.Employee> employees = protocalBufferRepository.findAll()
                .stream().map(data ->
                {
                    EmployeeBookProtos.Employee employee = EmployeeBookProtos.Employees.newBuilder().addEmployeesBuilder()
                            .setEmployeeId(data.getEmployeeId().intValue())
                            .setName(data.getName())
                            .setSalary(data.getSalary())
                            .setDepartment(data.getDepartment())
                            .setPhoneNumber(data.getPhoneNumber())
                            .setCreateDate(data.getCreateDate().toString())
                            .setUpdateDate(data.getUpdateDate().toString()).build();

                    return employee;
                }).collect(Collectors.toList());
        return EmployeeBookProtos.Employees.newBuilder().addAllEmployees(employees).build();
    }

    public Optional<EmployeeDetails> getEmployeeDetailsBasedOnId(Long employeeId){
        employeeDetailsByID = protocalBufferRepository.findById(employeeId);
        return employeeDetailsByID;
    }

    public EmployeeDetails insertEmployeeDetails(EmployeeDetails employee){
        Response = protocalBufferRepository.save(new EmployeeDetails(employee.getEmployeeId()
                                                                    ,employee.getName()
                                                                    ,employee.getDepartment()
                                                                    ,employee.getCreateDate()
                                                                    ,employee.getUpdateDate()
                                                                    ,employee.getPhoneNumber()
                                                                    ,employee.getSalary()));
        return Response;
    }
}
