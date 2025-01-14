package com.ptit.controller;

import com.ptit.dto.BaseResponse;
import com.ptit.model.Classes;
import com.ptit.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @PostMapping
    public ResponseEntity<BaseResponse> createClasses(@RequestBody Classes classes) {
        Classes createdClasses = classesService.createClasses(classes);
        BaseResponse response = BaseResponse.builder()
                .code(201)
                .message("Classes created successfully")
                .data(createdClasses)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getClassesById(@PathVariable int id) {
        BaseResponse response = BaseResponse.builder()
                .code(200)
                .data(classesService.getClassesWithStudentsById(id))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllClasses() {
        Iterable<Classes> classes = classesService.getAllClasses();
        BaseResponse response = BaseResponse.builder()
                .code(200)
                .data(classes)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateClasses(@PathVariable int id, @RequestBody Classes classesDetails) {
        Classes updatedClasses = classesService.updateClasses(id, classesDetails);
        BaseResponse response = updatedClasses != null ? BaseResponse.builder()
                .code(200)
                .message("Classes updated successfully")
                .data(updatedClasses)
                .build() : BaseResponse.builder()
                .code(404)
                .message("Classes not found")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteClasses(@PathVariable int id) {
        classesService.deleteClasses(id);
        BaseResponse response = BaseResponse.builder()
                .code(204)
                .message("Classes deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{classId}/students")
    public ResponseEntity<BaseResponse> addStudentsToClass(@PathVariable int classId, @RequestBody List<Integer> studentIds) {
        boolean isAdded = classesService.addStudentsToClass(classId, studentIds);
        BaseResponse response = isAdded ? BaseResponse.builder()
                .code(200)
                .message("Students added to class successfully")
                .build() : BaseResponse.builder()
                .code(404)
                .message("Class or some Students not found")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{classId}/students")
    public ResponseEntity<BaseResponse> removeStudentsFromClass(@PathVariable int classId, @RequestBody List<Integer> studentIds) {
        boolean isRemoved = classesService.removeStudentsFromClass(classId, studentIds);
        BaseResponse response = isRemoved ? BaseResponse.builder()
                .code(200)
                .message("Students removed from class successfully")
                .build() : BaseResponse.builder()
                .code(404)
                .message("Class or some Students not found")
                .build();
        return ResponseEntity.ok(response);
    }
}