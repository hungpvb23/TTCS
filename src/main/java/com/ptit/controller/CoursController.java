package com.ptit.controller;

import com.ptit.dto.BaseResponse;
import com.ptit.model.Cours;
import com.ptit.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @PostMapping
    public ResponseEntity<BaseResponse> createCours(@RequestBody Cours cours) {
        Cours createdCours = coursService.createCours(cours);
        BaseResponse response = BaseResponse.builder()
                .code(201)
                .message("Cours created successfully")
                .data(createdCours)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCoursById(@PathVariable int id) {
        Optional<Cours> cours = coursService.getCoursById(id);
        BaseResponse response = cours.map(value -> BaseResponse.builder()
                .code(200)
                .data(value)
                .build()).orElseGet(() -> BaseResponse.builder()
                .code(404)
                .message("Cours not found")
                .build());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllCours() {
        Iterable<Cours> cours = coursService.getAllCours();
        BaseResponse response = BaseResponse.builder()
                .code(200)
                .data(cours)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCours(@PathVariable int id, @RequestBody Cours coursDetails) {
        Cours updatedCours = coursService.updateCours(id, coursDetails);
        BaseResponse response = updatedCours != null ? BaseResponse.builder()
                .code(200)
                .message("Cours updated successfully")
                .data(updatedCours)
                .build() : BaseResponse.builder()
                .code(404)
                .message("Cours not found")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCours(@PathVariable int id) {
        coursService.deleteCours(id);
        BaseResponse response = BaseResponse.builder()
                .code(204)
                .message("Cours deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}