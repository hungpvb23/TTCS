package com.ptit.controller;

import com.ptit.dto.BaseResponse;
import com.ptit.dto.StudentSearchInput;
import com.ptit.model.Student;
import com.ptit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<BaseResponse> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        BaseResponse response = BaseResponse.builder()
                .code(201)
                .message("Student created successfully")
                .data(createdStudent)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentService.getStudentById(id);
        BaseResponse response = student.map(value -> BaseResponse.builder()
                .code(200)
                .data(value)
                .build()).orElseGet(() -> BaseResponse.builder()
                .code(404)
                .message("Student not found")
                .build());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllStudents() {
        Iterable<Student> students = studentService.getAllStudents();
        BaseResponse response = BaseResponse.builder()
                .code(200)
                .data(students)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        BaseResponse response = updatedStudent != null ? BaseResponse.builder()
                .code(200)
                .message("Student updated successfully")
                .data(updatedStudent)
                .build() : BaseResponse.builder()
                .code(404)
                .message("Student not found")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        BaseResponse response = BaseResponse.builder()
                .code(204)
                .message("Student deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<BaseResponse> searchStudents(@RequestBody StudentSearchInput searchCriteria) {
        List<Student> students = studentService.searchStudents(searchCriteria);
        BaseResponse response = BaseResponse.builder()
                .code(200)
                .data(students)
                .build();
        return ResponseEntity.ok(response);
    }
}