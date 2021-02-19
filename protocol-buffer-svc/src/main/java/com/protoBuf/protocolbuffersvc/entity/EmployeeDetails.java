package com.protoBuf.protocolbuffersvc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "employee", schema = "employee_details")
public class EmployeeDetails {

    @Id
    @Column(name = "employeeid")
    private Long employeeId;

    @Column(name="emp_name")
    private String name;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "salary")
    private int salary;

    @Column(name = "department")
    private String department;

    @Column(name = "createdate")
    private LocalDate createDate;

    @Column(name = "updatedate")
    private LocalDate updateDate;

      public EmployeeDetails(Long employeeId, String name, String department, LocalDate createDate, LocalDate updateDate, String phoneNumber, int salary) {
    }

    public EmployeeDetails() {

    }
}
