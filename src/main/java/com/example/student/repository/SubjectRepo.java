package com.example.student.repository;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer> {
    // public Subject findByStudentId(int id);
    public List<Subject> findByStudentId(int id);
}
