package com.example.student.service;

@Service
public class DemoService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    public ResponseBody getStudentDetails(int studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NoStudentException("No such Student with Id " + studentId + " found"));
        List<Subject> subjects = subjectRepo.findByStudentId(studentId);

        ResponseBody responseBody = new ResponseBody();
        responseBody.setStudentId(student.getStudentId());
        responseBody.setStudentName(student.getName());
        responseBody.setAge(student.getAge());
        responseBody.setSubjects(subjects);

        return responseBody;
    }

    public StudentNew getStudentApi() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/result/2", StudentNew.class);
    }

    public String postDetails(RequestBodyDto requestBodyDto) {
        Student student = new Student();
        student.setStudentId(requestBodyDto.getStudentId());
        student.setName(requestBodyDto.getStudentName());
        student.setAge(requestBodyDto.getAge());
        studentRepo.save(student);

        List<Subject> subEntityList = new ArrayList<>();
        for (Subject subjectDto : requestBodyDto.getSubjects()) {
            Subject subEntity = new Subject();
            subEntity.setId(subjectDto.getId());
            subEntity.setStudentId(student.getStudentId());
            subEntity.setSubjectName(subjectDto.getSubjectName());
            subEntity.setMarks(subjectDto.getMarks());
            subEntityList.add(subEntity);
        }

        subjectRepo.saveAll(subEntityList);

        return "Success";
    }

    public String delete(int studentId) {
        studentRepo.deleteById(studentId);
        subjectRepo.deleteAll(subjectRepo.findByStudentId(studentId));
        return "deleted";
    }

    public Student updateStudent(int studentId, RequestBodyDto requestBodyDto) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NoStudentException("No such Student with Id " + studentId + " found"));
        student.setName(requestBodyDto.getStudentName());
        student.setAge(requestBodyDto.getAge());
        studentRepo.save(student);

        List<Subject> subEntityList = new ArrayList<>();
        for (Subject subjectDto : requestBodyDto.getSubjects()) {
            Subject subEntity = new Subject();
            subEntity.setId(subjectDto.getId());
            subEntity.setStudentId(studentId);
            subEntity.setSubjectName(subjectDto.getSubjectName());
            subEntity.setMarks(subjectDto.getMarks());
            subEntityList.add(subEntity);
        }

        subjectRepo.saveAll(subEntityList);

        return student;
    }
}
