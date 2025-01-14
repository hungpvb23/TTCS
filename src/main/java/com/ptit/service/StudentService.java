package com.ptit.service;

import com.ptit.dto.StudentSearchInput;
import com.ptit.model.Student;
import com.ptit.repo.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Student createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        savedStudent.setStudentCode(String.format("B23DTCN%03d", savedStudent.getId()));
        return studentRepository.save(savedStudent);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(int id, Student studentDetails) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setDateOfBirth(studentDetails.getDateOfBirth());
            student.setGender(studentDetails.getGender());
            student.setEmail(studentDetails.getEmail());
            student.setPhone(studentDetails.getPhone());
            student.setAddress(studentDetails.getAddress());
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudents(StudentSearchInput criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getFirstName() != null && !criteria.getFirstName().isEmpty()) {
            predicates.add(cb.like(root.get("firstName"), "%" + criteria.getFirstName() + "%"));
        }
        if (criteria.getLastName() != null && !criteria.getLastName().isEmpty()) {
            predicates.add(cb.like(root.get("lastName"), "%" + criteria.getLastName() + "%"));
        }
        if (criteria.getGender() != null && !criteria.getGender().isEmpty()) {
            predicates.add(cb.like(root.get("gender"), "%" + criteria.getGender() + "%"));
        }
        if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
            predicates.add(cb.like(root.get("email"), "%" + criteria.getEmail() + "%"));
        }
        if (criteria.getPhone() != null && !criteria.getPhone().isEmpty()) {
            predicates.add(cb.like(root.get("phone"), "%" + criteria.getPhone() + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
