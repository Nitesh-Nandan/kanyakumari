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
        create();
        doQuery();
        doQuery2();
    }

    private void create() {

        Employee emp1 = Employee.builder().name("Nitesh").build();
        Employee emp2 = Employee.builder().name("Nandan").build();
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);

        Project p1 = Project.builder().name("Project 1").build();
        Project p2 = Project.builder().name("Project 2").build();
        Project p3 = Project.builder().name("Project 3").build();
        Project p4 = Project.builder().name("Project 4").build();
        projectRepository.save(p1);
        projectRepository.save(p2);
        projectRepository.save(p3);
        projectRepository.save(p4);

        // employee 1 projects
        List<Project> projects = new ArrayList<>();
        projects.add(p1); projects.add(p2);
//        projects.add(p3);
        emp1.setProjects(projects);
        employeeRepository.save(emp1);


//        // employee 2 projects
//        projects.clear();
//        projects.add(p2);projects.add(p3);projects.add(p4);
//        emp2.setProjects(projects);
//        employeeRepository.save(emp2);
//
//
//       // adding employee to projects
//        Employee emp3 = Employee.builder().name("John").build();
//        Employee emp4 = Employee.builder().name("John").build();
//        employeeRepository.save(emp3);
//        employeeRepository.save(emp4);
//        Project p5 = Project.builder().name("Project 5").build();
//        projectRepository.save(p5);
//        List<Employee> empList = new ArrayList<>();
//        empList.add(emp3); empList.add(emp4);
//        p5.setEmployees(empList);
//        projectRepository.save(p5);
    }

    private void doQuery(){

    }

    private void doQuery2() {

    }
}
