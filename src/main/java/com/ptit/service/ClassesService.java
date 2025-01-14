package com.ptit.service;

import com.ptit.dto.ClassesWithStudentsDTO;
import com.ptit.dto.StudentDTO;
import com.ptit.model.Classes;
import com.ptit.model.Student;
import com.ptit.model.StudentClass;
import com.ptit.repo.ClassesRepository;
import com.ptit.repo.StudentClassRepository;
import com.ptit.repo.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentClassRepository studentClassRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Classes createClasses(Classes classes) {
        return classesRepository.save(classes);
    }

    public Optional<Classes> getClassesById(int id) {
        return classesRepository.findById(id);
    }

    public Iterable<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    public Classes updateClasses(int id, Classes classesDetails) {
        Optional<Classes> optionalClasses = classesRepository.findById(id);
        if (optionalClasses.isPresent()) {
            Classes existingClasses = optionalClasses.get();
            existingClasses.setClassName(classesDetails.getClassName());
            existingClasses.setTeacherName(classesDetails.getTeacherName());
            existingClasses.setClassCode(classesDetails.getClassCode());
            existingClasses.setUpdatedAt(LocalDateTime.now());
            return classesRepository.save(existingClasses);
        } else {
            return null;
        }
    }

    public void deleteClasses(int id) {
        classesRepository.deleteById(id);
    }

    public ClassesWithStudentsDTO getClassesWithStudentsById(int id) {
        Optional<Classes> classesOptional = classesRepository.findById(id);
        if (classesOptional.isPresent()) {
            Classes classes = classesOptional.get();
            List<StudentDTO> students = entityManager.createNativeQuery(
                            "SELECT s.student_id, s.first_name, s.last_name, s.date_of_birth, s.gender, s.email, s.phone, s.address " +
                                    "FROM students s " +
                                    "INNER JOIN student_classes sc ON s.student_id = sc.student_id " +
                                    "WHERE sc.class_id = :classId")
                    .setParameter("classId", id)
                    .unwrap(org.hibernate.query.NativeQuery.class)
                    .setResultTransformer((tuple, aliases) -> new StudentDTO(
                            (Integer) tuple[0],
                            (String) tuple[1],
                            (String) tuple[2],
                            ((java.sql.Date) tuple[3]).toLocalDate(),
                            (String) tuple[4],
                            (String) tuple[5],
                            (String) tuple[6],
                            (String) tuple[7]
                    ))
                    .getResultList();

            ClassesWithStudentsDTO dto = new ClassesWithStudentsDTO();
            dto.setId(classes.getId());
            dto.setClassName(classes.getClassName());
            dto.setTeacherName(classes.getTeacherName());
            dto.setClassCode(classes.getClassCode());
            dto.setStudents(students.stream().collect(Collectors.toList()));
            return dto;
        }
        return null;
    }

    public boolean addStudentsToClass(int classId, List<Integer> studentIds) {
        Optional<Classes> optionalClass = classesRepository.findById(classId);
        if (optionalClass.isPresent()) {
            for (int studentId : studentIds) {
                Optional<Student> optionalStudent = studentRepository.findById(studentId);
                if (optionalStudent.isPresent()) {
                    StudentClass studentClass = new StudentClass();
                    studentClass.setClassId(classId);
                    studentClass.setStudentId(studentId);
                    studentClassRepository.save(studentClass);
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudentsFromClass(int classId, List<Integer> studentIds) {
        Optional<Classes> optionalClass = classesRepository.findById(classId);
        if (optionalClass.isPresent()) {
            for (int studentId : studentIds) {
                Optional<StudentClass> studentClassOptional = studentClassRepository.findByClassIdAndStudentId(classId, studentId);
                if (studentClassOptional.isPresent()) {
                    studentClassRepository.delete(studentClassOptional.get());
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
