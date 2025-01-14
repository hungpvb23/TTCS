package com.ptit.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "classes")
@Data
public class Classes extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('classes_class_id_seq')")
    @Column(name = "class_id", nullable = false)
    private Integer id;

    @Column(name = "class_name", nullable = false, length = 100)
    private String className;

    @Column(name = "teacher_name", nullable = false, length = 100)
    private String teacherName;

    @Column(name = "class_code", nullable = false, length = 20)
    private String classCode;
}