package com.example.student.dto;
@Getter
@Setter
@ToString
public class RequestBodyDto {
    private int studentId;
    private String studentName;
    private int age;
    private List<Subject> subjects;
}
