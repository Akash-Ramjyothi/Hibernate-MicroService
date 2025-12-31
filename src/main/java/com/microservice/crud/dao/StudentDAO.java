package com.microservice.crud.dao;

import com.microservice.crud.entity.Student;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object for {@link Student} entity.
 * Provides CRUD operations and common query helpers.
 */
public interface StudentDAO {

    /**
     * Persist a new student entity.
     */
    void save(Student student);

    /**
     * Find a student by primary key.
     *
     * @param id student identifier
     * @return Optional containing Student if found
     */
    Optional<Student> findById(Integer id);

    /**
     * Retrieve all students.
     */
    List<Student> findAll();

    /**
     * Find students by last name.
     */
    List<Student> findByLastName(String lastName);

    /**
     * Update an existing student.
     */
    void update(Student student);

    /**
     * Delete a student by id.
     */
    void deleteById(Integer id);

    /**
     * Check if a student exists by id.
     */
    boolean existsById(Integer id);

    /**
     * Count total students.
     */
    long count();

    /**
     * Delete all students.
     *
     * @return number of rows deleted
     */
    int deleteAll();
}
