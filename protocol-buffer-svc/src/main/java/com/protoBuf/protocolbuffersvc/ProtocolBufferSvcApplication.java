package com.protoBuf.protocolbuffersvc;

import com.protoBuf.protocolbuffersvc.entity.EmployeeDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ProtocolBufferSvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProtocolBufferSvcApplication.class, args);
	}

	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}
}
