package com.example.student.serviceTest;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DemoServiceTest {

    @MockBean
    private StudentRepo studentRepoMock;

    @MockBean
    private SubjectRepo subjectRepoMock;

    @Autowired
    private DemoService demoService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @MockBean
    private RestTemplate restTemplate;

    Student student = null;
    Subject subject = null;
    StudentNew studentNew = null;
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

        studentNew = new StudentNew();
        studentNew.setStudentId(2);
        studentNew.setStudentName("lmnop");
    }

    @Test
    void testGetApi() {
        Mockito.when(restTemplate.getForEntity("http://localhost:8081/result/1", StudentNew.class))
               .thenReturn(new ResponseEntity<>(studentNew, HttpStatus.OK));

        StudentNew result = demoService.getStudentApi();
        Assertions.assertEquals(studentNew.getStudentId(), result.getStudentId());
    }

    @Test
    void testGetDetailsService() throws Exception {
        Mockito.when(studentRepoMock.findAll()).thenReturn(students);
        Mockito.when(subjectRepoMock.findByStudentId(student.getStudentId())).thenReturn(subjects);

        ResponseBody response = demoService.getStudentDetails(1);
        Assertions.assertEquals(1, response.getStudentId());
        Assertions.assertEquals(2, response.getSubjects().size());
    }

    @Test
    void testPostDetailsService() throws Exception {
        Mockito.when(studentRepoMock.save(student)).thenReturn(student);
        Mockito.when(subjectRepoMock.saveAll(subjects)).thenReturn(subjects);

        RequestBodyDto requestBodyDto = new RequestBodyDto();
        requestBodyDto.setStudentId(student.getStudentId());
        requestBodyDto.setStudentName(student.getName());
        requestBodyDto.setAge(student.getAge());
        requestBodyDto.setSubjects(subjects);

        String result = demoService.postDetails(requestBodyDto);
        Assertions.assertEquals("Success", result);
    }

    @Test
    void testDeleteStudent() throws Exception {
        Mockito.doNothing().when(studentRepoMock).deleteById(Mockito.anyInt());
        Mockito.doNothing().when(subjectRepoMock).deleteById(Mockito.anyInt());

        String result = demoService.delete(1);
        Assertions.assertEquals("deleted", result);
    }

    @Test
    void testUpdateStudent() throws Exception {
        Mockito.when(studentRepoMock.save(student)).thenReturn(student);
        Mockito.when(subjectRepoMock.saveAll(subjects)).thenReturn(subjects);

        RequestBodyDto requestBodyDto = new RequestBodyDto();
        requestBodyDto.setStudentId(student.getStudentId());
        requestBodyDto.setStudentName(student.getName());
        requestBodyDto.setAge(student.getAge());
        requestBodyDto.setSubjects(subjects);

        Student result = demoService.updateStudent(1, requestBodyDto);
        Assertions.assertEquals(1, result.getStudentId());
    }
}
