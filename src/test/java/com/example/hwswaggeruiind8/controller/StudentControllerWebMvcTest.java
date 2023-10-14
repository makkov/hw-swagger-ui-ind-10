package com.example.hwswaggeruiind8.controller;

import com.example.hwswaggeruiind8.enitity.Student;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import com.example.hwswaggeruiind8.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @Test
    void create() throws Exception {
        //Подготовка входных данных
        Student studentForCreate = new Student("Иван", 20);

        String request = objectMapper.writeValueAsString(studentForCreate);

        Student studentWithId = new Student("Иван", 20);
        long id = 1l;
        studentWithId.setId(id);

        //Подготовка ожидаемого результат
        when(studentRepository.save(studentForCreate)).thenReturn(studentWithId);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(studentForCreate.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(studentForCreate.getAge()));

        verify(studentRepository).save(studentForCreate);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void get() throws Exception {
        //Подготовка входных данных
        long id = 1l;

        //Подготовка ожидаемого результат
        Student studentWithId = new Student("Иван", 20);
        studentWithId.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(studentWithId));

        //Начало теста
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/student")
                                .param("id", String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(studentWithId.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(studentWithId.getAge()));

        verify(studentRepository).findById(id);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void delete() throws Exception {
        long id = 1l;

        //Подготовка ожидаемого результат
        Student studentWithId = new Student("Иван", 20);
        studentWithId.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(studentWithId));
        doNothing().when(studentRepository).deleteById(id);

        //Начало теста
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/student")
                                .param("id", String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(studentWithId.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(studentWithId.getAge()));

        verify(studentRepository).findById(id);
        verify(studentRepository).deleteById(id);
        verifyNoMoreInteractions(studentRepository);
    }
}