package com.microservice.crud;

import com.microservice.crud.dao.StudentDAO;
import com.microservice.crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
//            createMultipleStudents(studentDAO);
//            readStudent(studentDAO);
//            queryForStudent(studentDAO);
//            queryForStudentsByLastName(studentDAO);
            updateStudent(studentDAO);
        };
    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("🫘 Creating the student.");
        Student tempStudent = new Student("Akash", "Ramjyothi", "akash.ramjyothi@gmail.com");

        System.out.println("💾 Saving student.");
        studentDAO.save(tempStudent);

        System.out.println("🫆 Displaying Student ID: " + tempStudent.getId());
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        System.out.println("🫘 Creating multiple students.");
        Student tempStudent1 = new Student("Batman", "Dark Knight", "batman@gmail.com");
        Student tempStudent2 = new Student("Peacemaker", "Dragon", "peacemaker@gmail.com");
        Student tempStudent3 = new Student("Jackal", "Alexander", "jackal@gmail.com");

        System.out.println("💾 Saving multiple students.");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void readStudent(StudentDAO studentDAO) {
        System.out.println("🫘 Creating new student.");
        Student tempStudent = new Student("Green Lantern", "Ring Master", "green@gmail.com");

        System.out.println("💾 Saving the student.");
        studentDAO.save(tempStudent);

        int theId = tempStudent.getId();
        System.out.println("🫆 Saved student Generated ID: " + theId);

        System.out.println("🪪 Retreiving student with ID: " + theId);
        Student myStudent = studentDAO.findById(theId);

        System.out.println("✅ Found Student: " + myStudent);
    }

    private void queryForStudent(StudentDAO studentDAO) {
        List<Student> theStudents = studentDAO.findAll();

        for (Student tempStudent : theStudents) {
            System.out.println("👨‍🎓 student: " + tempStudent);
        }
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        List<Student> theStudents = studentDAO.findByLastName("Ring Master");

        for (Student tempStudent : theStudents) {
            System.out.println("👨‍🎓 student: " + tempStudent);
        }
    }

    private void updateStudent(StudentDAO studentDAO) {
        System.out.println("👨 studentDAO: " + studentDAO);

        int id = 1;
        Student theStudent = studentDAO.findById(id);
        System.out.println("🎉 Found theStudent: " + theStudent);

        theStudent.setFirstName("Obi-Wan");
        studentDAO.update(theStudent);

        System.out.println("🏓 theStudent: " + theStudent);
    }

}
