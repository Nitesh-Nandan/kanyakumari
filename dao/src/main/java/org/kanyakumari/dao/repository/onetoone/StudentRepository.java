package org.kanyakumari.dao.repository.onetoone;

import org.kanyakumari.dao.entity.onetoone.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
