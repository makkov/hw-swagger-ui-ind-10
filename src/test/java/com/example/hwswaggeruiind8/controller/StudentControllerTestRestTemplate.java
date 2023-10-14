package com.example.hwswaggeruiind8.controller;

import com.example.hwswaggeruiind8.enitity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    void create_success() {
        //Подготовка входных данных
        Student studentForCreate = new Student("Иван", 20);

        //Подготовка ожидаемого результата
        Student expectedStudent = new Student("Иван", 20);

        //Начало теста
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        assertThat(postedStudent).isNotNull();
        assertEquals(expectedStudent.getName(), postedStudent.getName());
        assertEquals(expectedStudent.getAge(), postedStudent.getAge());
    }

    @Test
    void get() {
        //Подготовка входных данных
        //todo: переделать get - доработать исключением
//        Student studentForCreate = new Student("Иван", 20);
//
//        //Начало теста
//        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
//        Optional<Student> actualStudentOpt = this.restTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + postedStudent.getId(), Optional<Student>.class);
//        assertEquals(postedStudent.getName(), actualStudentOpt.get().getName());
//        assertEquals(postedStudent.getAge(), actualStudentOpt.get().getAge());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        //Подготовка входных данных
        Student studentForDelete = new Student("Иван", 20);

        //Начало теста
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForDelete, Student.class);
        this.restTemplate.delete("http://localhost:" + port + "/student" + "?id=" + postedStudent.getId());
        //проверяем, что такого студента после удаления нет в базе
        Optional<Student> studentOpt = this.restTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + postedStudent.getId(), Optional.class);
        assertTrue(studentOpt.isEmpty());
    }

    @Test
    void getByAge() {
    }

    @Test
    void getByAgeBetween() {
    }

    @Test
    void getFacultyByStudentId() {
    }
}