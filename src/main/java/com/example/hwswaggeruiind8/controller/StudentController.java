package com.example.hwswaggeruiind8.controller;

import com.example.hwswaggeruiind8.enitity.Avatar;
import com.example.hwswaggeruiind8.enitity.Faculty;
import com.example.hwswaggeruiind8.enitity.Student;
import com.example.hwswaggeruiind8.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.add(student.getName(), student.getAge());
    }

    @GetMapping
    public Optional<Student> get(@RequestParam long id) {
        return studentService.get(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student.getId(), student.getName(), student.getAge());
    }

    @DeleteMapping
    public Student delete(@RequestParam long id) {
        return studentService.delete(id);
    }

    @GetMapping("/by-age")
    public List<Student> getByAge(@RequestParam int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/by-age-between")
    public List<Student> getByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getByAgeBetween(min, max);
    }

    @GetMapping("/faculty-by-student-id")
    public Faculty getFacultyByStudentId(@RequestParam Long id) {
        return studentService.getFacultyByStudentId(id);
    }

    @GetMapping("/count")
    public Integer getCount() {
        return studentService.getCount();
    }

    @GetMapping("/avg-age")
    public Double getAvgAge() {
        return studentService.getAvgAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFive() {
        return studentService.getLastFive();
    }

    @GetMapping("/name/start-with-a")
    public List<String> getNamesStartWithA() {
        return studentService.getAllWhereNameStartWithA();
    }

    @GetMapping("/avg-age-with-stream")
    public double getAvgAgeWithStream() {
        return studentService.getAvgAgeWithStream();
    }

    @GetMapping("/print-in-threads")
    public void printInThreads() {
        studentService.printStudents();
    }

    @GetMapping("/print-in-threads-sync")
    public void printInThreadsSync() {
        studentService.printStudentsSync();
    }
}
