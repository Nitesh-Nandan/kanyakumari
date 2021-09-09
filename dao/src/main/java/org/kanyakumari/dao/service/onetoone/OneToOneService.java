package org.kanyakumari.dao.service.onetoone;

import lombok.extern.slf4j.Slf4j;
import org.kanyakumari.dao.entity.onetoone.Laptop;
import org.kanyakumari.dao.entity.onetoone.Student;
import org.kanyakumari.dao.repository.onetoone.LaptopRepository;
import org.kanyakumari.dao.repository.onetoone.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OneToOneService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    public void main() {
        log.info("OneToOneService started executing ..............................");

        create();
        query();
        query2();

        log.info("OneToOneService Finished executing ..............................");
    }

    private void create() {
        log.info("Create Started .....");
        Laptop laptop = Laptop.builder().laptopName("Laptop 1").build();
        laptopRepository.save(laptop);
        Student student = Student.builder().studentName("Student Name 1").build();
        student.setLaptop(laptop);
        studentRepository.save(student);

        log.info("Create Finished .....");
    }

    private void query() {
        log.info("Query Started .....");
        Optional<Student> qs1 = studentRepository.findById(1L);
        if (qs1.isPresent()) {
            System.out.println("Student Id : " + qs1.get().getId());
            System.out.println("Student Name : " + qs1.get().getStudentName());
            System.out.println("Laptop Name: " + qs1.get().getLaptop().getLaptopName());
        }
        log.info("Query Finished .....");

    }

    private void query2() {
        log.info("Query2 Started .....");

        Optional<Laptop> laptop = laptopRepository.findById(1L);
        if (laptop.isPresent()) {
            System.out.println("Laptop ID : " + laptop.get().getId());
            System.out.println("Laptop Name : " + laptop.get().getLaptopName());
            // this statement throw null pointer exception as mapping is unidirectional
           // System.out.println("Student Name: " + laptop.get().getStudent().getStudentName());
        }

        log.info("Query2 Finished .....");
    }

}
