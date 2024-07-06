package com.example.student.repositoryTest;

@DataJpaTest
class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    Student student = null;
    Subject subject = null;
    List<Subject> subjects = null;
    List<Student> students = null;

    @BeforeEach
    void setUpTestData() {
        student = new Student();
        students = new ArrayList<>();
        student.setStudentId(1);
        student.setName("Anushna");
        student.setAge(21);
        students.add(student);

        subject = new Subject();
        subjects = new ArrayList<>();
        subject.setId(1);
        subject.setSubjectName("Maths");
        subject.setMarks(98);
        subjects.add(subject);

        subject = new Subject();
        subject.setId(2);
        subject.setSubjectName("Science");
        subject.setMarks(95);
        subjects.add(subject);

        studentRepo.save(student);
    }

    @Test
    void testSave() {
        Student student = new Student();
        student.setStudentId(2);
        student.setName("Panwar");
        student.setAge(22);

        studentRepo.save(student);

        Optional<Student> newStudent = studentRepo.findById(2);
        assertTrue(newStudent.isPresent());
        Assertions.assertEquals(student.getStudentId(), newStudent.get().getStudentId());
    }

    @Test
    void testFindById() {
        Student student = studentRepo.findById(1).get();
        assertTrue(student.getStudentId() == 1);
        Assertions.assertEquals("Anushna", student.getName());
    }

    @Test
    void testDeleteById() {
        studentRepo.deleteById(1);
        Optional<Student> student = studentRepo.findById(1);
        assertFalse(student.isPresent());
    }
}
