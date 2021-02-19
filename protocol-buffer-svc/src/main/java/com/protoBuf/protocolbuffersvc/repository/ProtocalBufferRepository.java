package com.protoBuf.protocolbuffersvc.repository;

import com.protoBuf.protocolbuffersvc.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocalBufferRepository extends JpaRepository<EmployeeDetails, Long>{
}
