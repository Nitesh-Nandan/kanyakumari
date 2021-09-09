package org.kanyakumari.dao.repository.manytomany;

import org.kanyakumari.dao.entity.manytomany.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
