package com.example.student.controller;

@RestController
@RequestMapping("/result")
public class DemoController {

    @Autowired
    DemoService ds;

    @GetMapping(path = "/getStudentDetails/{Id}")
    public ResponseBody getStudentDetails(@PathVariable int Id) {
        try {
            return ds.getStudentDetails(Id);
        } catch (Exception e) {
            throw new NoStudentException("No such Student with Id " + Id + " found");
        }
    }

    @GetMapping(path="/api")
    public StudentNew consumeApi(){
        return ds.getStudentApi();
    }

    @PostMapping(path = "/postStudentDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postStudentDetails(@RequestBody RequestBodyDto requestBody) {
        return ds.postDetails(requestBody);
    }

    @DeleteMapping(path = "/deleteDetails/{id}")
    public String delete(@PathVariable int id) {
        ds.delete(id);
        return "deleted";
    }

    @PutMapping(path = "/putDetails/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable int id, @RequestBody RequestBodyDto requestBodyDto) {
        ds.updateStudent(id, requestBodyDto);
        return "Updated";
    }
}

