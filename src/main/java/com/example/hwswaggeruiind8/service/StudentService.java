package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static long COUNTER = 0;

    private final Map<Long, Student> studentById = new HashMap<>();

    public Student add(String name, int age) {
        Student newStudent = new Student(++COUNTER, name, age);
        studentById.put(newStudent.getId(), newStudent);
        return newStudent;
    }

    public Student get(long id) {
        return studentById.get(id);
    }

    public Student update(long id, String name, int age) {
        Student studentForUpdate = studentById.get(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentForUpdate;
    }

    public Student delete(long id) {
        Student studentForDelete = studentById.get(id);
        studentById.remove(id);
        return studentForDelete;
    }

    public List<Student> getByAge(int age) {
        return studentById.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
