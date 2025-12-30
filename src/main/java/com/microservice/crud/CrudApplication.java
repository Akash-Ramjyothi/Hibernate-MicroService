package com.microservice.crud;

import com.microservice.crud.dao.StudentDAO;
import com.microservice.crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        SpringApplication.run(CrudApplication.class, args);

        System.out.println(
                "🚀 CRUD Application started in " +
                (System.currentTimeMillis() - startTime) + " ms"
        );
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO, Environment environment) {
        return args -> {

            logStartupInfo(environment);

            // Enable demo operations explicitly
            if (isDemoMode(environment)) {
                runCrudDemo(studentDAO);
            } else {
                System.out.println("ℹ️ Demo mode disabled. Skipping DB operations.");
            }
        };
    }

    /* ===================== DEMO FLOW ===================== */

    private void runCrudDemo(StudentDAO studentDAO) {
        System.out.println("🧪 Running CRUD demo operations...");

        createMultipleStudents(studentDAO);
        listAllStudents(studentDAO);
    }

    private boolean isDemoMode(Environment environment) {
        return Boolean.parseBoolean(
                environment.getProperty("app.demo.enabled", "false")
        );
    }

    private void logStartupInfo(Environment environment) {
        String[] profiles = environment.getActiveProfiles();
        String activeProfiles = profiles.length == 0
                ? "default"
                : String.join(", ", profiles);

        System.out.println(
                "🕒 Startup Time : " + LocalDateTime.now() +
                " | Active Profiles : " + activeProfiles
        );
    }

    /* ===================== CRUD OPERATIONS ===================== */

    private void createMultipleStudents(StudentDAO studentDAO) {
        System.out.println("🫘 Creating multiple students");

        studentDAO.save(new Student("Batman", "Dark Knight", "batman@gmail.com"));
        studentDAO.save(new Student("Peacemaker", "Dragon", "peacemaker@gmail.com"));
        studentDAO.save(new Student("Jackal", "Alexander", "jackal@gmail.com"));

        System.out.println("💾 Students saved successfully");
    }

    private void listAllStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();
        students.forEach(student ->
                System.out.println("👨‍🎓 Student: " + student)
        );
    }

    /* ===================== OPTIONAL OPERATIONS ===================== */

    private void updateStudent(StudentDAO studentDAO, int id, String newFirstName) {
        Student student = studentDAO.findById(id);
        student.setFirstName(newFirstName);
        studentDAO.update(student);
        System.out.println("✏️ Updated Student: " + student);
    }

    private void deleteStudent(StudentDAO studentDAO, int studentId) {
        studentDAO.delete(studentId);
        System.out.println("🗑️ Deleted student with ID: " + studentId);
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        int rowsDeleted = studentDAO.deleteAll();
        System.out.println("🗑️ Total students deleted: " + rowsDeleted);
    }
}
