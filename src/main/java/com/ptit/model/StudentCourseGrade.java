package com.ptit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_course_grades")
@Data
public class StudentCourseGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "grade", nullable = false)
    private Double grade;
}