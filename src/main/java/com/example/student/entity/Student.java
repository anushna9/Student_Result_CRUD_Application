package com.example.student.entity;
@Entity
@Getter
@Setter
@Table(name = "STUDENT1")
public class Student {
    @Id
    private int studentId;
    private String name;
    private int age;
}
