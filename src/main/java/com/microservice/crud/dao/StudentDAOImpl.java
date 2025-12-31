package com.microservice.crud.dao;

import com.microservice.crud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query =
                entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query =
                entityManager.createQuery(
                        "FROM Student WHERE lastName = :lastName",
                        Student.class
                );
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        Student student = entityManager.find(Student.class, id);
        return student != null;
    }

    @Override
    public long count() {
        return entityManager
                .createQuery("SELECT COUNT(s) FROM Student s", Long.class)
                .getSingleResult();
    }

    @Override
    @Transactional
    public int deleteAll() {
        return entityManager
                .createQuery("DELETE FROM Student")
                .executeUpdate();
    }
}
