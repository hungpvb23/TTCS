package com.ptit.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('students_student_id_seq')")
    @Column(name = "student_id", nullable = false)
    private Integer id;

    @Column(name = "student_code", nullable = false, length = 50)
    private String studentCode;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "address", length = Integer.MAX_VALUE)
    private String address;

    @PrePersist
    public void generateStudentCode() {
        this.studentCode = String.format("B23DTCN%03d", this.id);
    }
}