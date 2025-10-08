package com.microservice.crud;

import com.microservice.crud.dao.StudentDAO;
import com.microservice.crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
            createMultipleStudents(studentDAO);
        };
    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("🫘 Creating the student.");
        Student tempStudent = new Student("Akash", "Ramjyothi", "akash.ramjyothi@gmail.com");

        System.out.println("💾 Saving student.");
        studentDAO.save(tempStudent);

        System.out.println("🫆 Displaying Student ID: " + tempStudent.getId());
    }

    private void createMultipleStudents(StudentDAO studentDAO){
        System.out.println("🫘 Creating multiple students.");
        Student tempStudent1 = new Student("Batman","Dark Knight","batman@gmail.com");
        Student tempStudent2 = new Student("Peacemaker","Dragon","peacemaker@gmail.com");
        Student tempStudent3 = new Student("Jackal","Alexander","jackal@gmail.com");

        System.out.println("💾 Saving multiple students.");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

}
