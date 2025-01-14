package com.ptit.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClassesWithStudentsDTO {
    private Integer id;
    private String className;
    private Integer courseId;
    private String teacherName;
    private String classCode;
    private List<StudentDTO> students;
}