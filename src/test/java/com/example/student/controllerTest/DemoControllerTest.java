package com.example.student.controllerTest;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoService demoService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    void testGetDetails() throws Exception {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setAge(22);
        responseBody.setStudentName("AN pw");
        responseBody.setStudentId(1);

        Mockito.when(demoService.getStudentDetails(1)).thenReturn(responseBody);

        mockMvc.perform(get("/result/getStudentDetails/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(1)));
    }

    @Test
    void testException() throws Exception {
        Mockito.when(demoService.getStudentDetails(16)).thenThrow(new NoStudentException("No Student Found"));

        mockMvc.perform(get("/result/getStudentDetails/16"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPostDetails() throws Exception {
        String req = "{\n" +
                "\"studentId\": 1,\n" +
                "\"studentName\": null,\n" +
                "\"age\": 20,\n" +
                "\"subjects\": [\n" +
                "{\n" +
                "\"id\": 11,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"Maths\",\n" +
                "\"marks\": 100\n" +
                "},\n" +
                "{\n" +
                "\"id\": 12,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"Science\",\n" +
                "\"marks\": 99\n" +
                "},\n" +
                "{\n" +
                "\"id\": 13,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"English\",\n" +
                "\"marks\": 95\n" +
                "},\n" +
                "{\n" +
                "\"id\": 14,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"SST\",\n" +
                "\"marks\": 96\n" +
                "}\n" +
                "]\n" +
                "}";

        Mockito.when(demoService.postDetails(Mockito.any(RequestBodyDto.class))).thenReturn("Success");

        mockMvc.perform(post("/result/postStudentDetails")
                .content(req)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Mockito.when(demoService.delete(3)).thenReturn("deleted");

        mockMvc.perform(delete("/result/deleteDetails/3"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        String req = "{\n" +
                "\"studentId\": 1,\n" +
                "\"studentName\": null,\n" +
                "\"age\": 20,\n" +
                "\"subjects\": [\n" +
                "{\n" +
                "\"id\": 11,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"Maths\",\n" +
                "\"marks\": 100\n" +
                "},\n" +
                "{\n" +
                "\"id\": 12,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"Science\",\n" +
                "\"marks\": 99\n" +
                "},\n" +
                "{\n" +
                "\"id\": 13,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"English\",\n" +
                "\"marks\": 80\n" +
                "},\n" +
                "{\n" +
                "\"id\": 14,\n" +
                "\"studentId\": 1,\n" +
                "\"subjectName\": \"SST\",\n" +
                "\"marks\": 94\n" +
                "}\n" +
                "]\n" +
                "}";

        Mockito.when(demoService.updateStudent(Mockito.anyInt(), Mockito.any(RequestBodyDto.class)))
                .thenReturn(new Student());

        mockMvc.perform(put("/result/putDetails/1")
                .content(req)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
