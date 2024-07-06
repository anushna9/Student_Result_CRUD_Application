package com.example.student.entity;

@Entity
@Getter
@Setter
@Table(name = "SUBJECT1")
public class Subject {
    @Id
    private int id;
    private int studentId;
    private String subjectName;
    private int marks;
}
