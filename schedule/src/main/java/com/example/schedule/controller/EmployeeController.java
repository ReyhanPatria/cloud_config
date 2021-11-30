package com.example.schedule.controller;

import com.example.schedule.model.pojo.EmployeePojo;
import com.example.schedule.repository.EmployeeFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final EmployeeFeignClient employeeFeignClient;

    @GetMapping("employees")
    public ResponseEntity<Iterable<EmployeePojo>> getAllEmployees() {
        log.info("All employee data is fetched");

        Iterable<EmployeePojo> eIterable = employeeFeignClient.getAllEmployee();
        return new ResponseEntity<>(eIterable, HttpStatus.OK);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeePojo> getEmployeeById(@PathVariable Integer id) {
        EmployeePojo employee = employeeFeignClient.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}