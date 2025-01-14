package com.ptit.controller;

import com.ptit.dto.BaseResponse;
import com.ptit.model.StudentCourseGrade;
import com.ptit.service.StudentCourseGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
public class StudentCourseGradeController {

    @Autowired
    private StudentCourseGradeService studentCourseGradeService;

    @PostMapping
    public ResponseEntity<BaseResponse> addGrade(@RequestBody StudentCourseGrade grade) {
        boolean isAdded = studentCourseGradeService.addGrade(grade);
        BaseResponse response = isAdded ? BaseResponse.builder()
                .code(200)
                .message("Grade added successfully")
                .build() : BaseResponse.builder()
                .code(400)
                .message("Failed to add grade")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateGrade(@PathVariable Integer id, @RequestBody StudentCourseGrade grade) {
        boolean isUpdated = studentCourseGradeService.updateGrade(id, grade);
        BaseResponse response = isUpdated ? BaseResponse.builder()
                .code(200)
                .message("Grade updated successfully")
                .build() : BaseResponse.builder()
                .code(404)
                .message("Grade not found")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteGrade(@PathVariable Integer id) {
        boolean isDeleted = studentCourseGradeService.deleteGrade(id);
        BaseResponse response = isDeleted ? BaseResponse.builder()
                .code(200)
                .message("Grade deleted successfully")
                .build() : BaseResponse.builder()
                .code(404)
                .message("Grade not found")
                .build();
        return ResponseEntity.ok(response);
    }
}