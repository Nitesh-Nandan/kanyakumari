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
        test1();
        doQuery();
        doQuery2();
    }

    private void test1() {
        Laptop laptop = Laptop.builder().laptopName("Laptop 1").build();
        Student student = Student.builder().studentName("Student Name 1").build();

        laptopRepository.save(laptop);
        student.setLaptop(laptop);
        studentRepository.save(student);
    }

    private void doQuery() {
        Optional<Student> qs1 = studentRepository.findById(1L);
        if(qs1.isPresent()) {
            System.out.println("Student Id : " + qs1.get().getId());
            System.out.println("Student Name : " + qs1.get().getStudentName());
            System.out.println("Laptop Name: " + qs1.get().getLaptop().getLaptopName());
        }

    }

    private void doQuery2() {
        Optional<Laptop> laptop = laptopRepository.findById(1L);
        if(laptop.isPresent()) {
            System.out.println("Laptop ID : " + laptop.get().getId());
            System.out.println("Laptop Name : " + laptop.get().getLaptopName());
            System.out.println("Student Name: " + laptop.get().getStudent().getStudentName());
        }
    }

}
