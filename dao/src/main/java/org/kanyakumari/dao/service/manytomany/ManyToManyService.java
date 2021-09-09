package org.kanyakumari.dao.service.manytomany;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.ElementMatcher;
import org.kanyakumari.dao.entity.manytomany.Employee;
import org.kanyakumari.dao.entity.manytomany.Project;
import org.kanyakumari.dao.repository.manytomany.EmployeeRepository;
import org.kanyakumari.dao.repository.manytomany.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ManyToManyService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void main() {
        log.info("ManyToManyService Started ....................................");
        create();
        query();
        query2();
        log.info("ManyToManyService Finished ....................................");
    }

    private void create() {

        log.info("Create started ........................");

        Employee emp1 = Employee.builder().name("Employee 1").build();
        Employee emp2 = Employee.builder().name("Employee 2").build();
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);

        Project p1 = Project.builder().name("Project 1").build();
        Project p2 = Project.builder().name("Project 2").build();
        Project p3 = Project.builder().name("Project 3").build();
        projectRepository.save(p1);
        projectRepository.save(p2);
        projectRepository.save(p3);

        // employee 1 projects (emp1 -> {p1, p2})
        List<Project> projects = new ArrayList<>();
        projects.add(p1);
        projects.add(p2);
        emp1.setProjects(projects);
        employeeRepository.save(emp1);


        // project 3 to employees (p3 -> {emp3, emp4})
        Employee emp3 = Employee.builder().name("Employee 3").build();
        Employee emp4 = Employee.builder().name("Employee 4").build();
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);
        List<Employee> empList = new ArrayList<>();
        empList.add(emp3);
        empList.add(emp4);
        p3.setEmployees(empList);
        projectRepository.save(p3);

        log.info("Create Finished ........................");
    }

    private void query() {

    }

    private void query2() {

    }
}
