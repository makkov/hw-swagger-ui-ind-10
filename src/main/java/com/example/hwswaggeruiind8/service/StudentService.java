package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.enitity.Faculty;
import com.example.hwswaggeruiind8.enitity.Student;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(String name, int age) {
        logger.info("Был вызван метод add");
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    public Optional<Student> get(long id) {
        logger.info("Был вызван метод get");
        return studentRepository.findById(id);
    }

    public Student update(long id, String name, int age) {
        logger.info("Был вызван метод update");
        Student studentForUpdate = studentRepository.findById(id).get();
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }

    public Student delete(long id) {
        logger.info("Был вызван метод delete");
        Student studentForDelete = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    public List<Student> getByAge(int age) {
        logger.info("Был вызван метод getByAge");
        return studentRepository.findAll().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Student> getByAgeBetween(int min, int max) {
        logger.info("Был вызван метод getByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Faculty getFacultyByStudentId(Long id) {
        logger.info("Был вызван метод getFacultyByStudentId");
        return studentRepository.findById(id).get().getFaculty();
    }

    public Integer getCount() {
        logger.info("Был вызван метод getCount");
        return studentRepository.getCount();
    }

    public Double getAvgAge() {
        logger.info("Был вызван метод getAvgAge");
        return studentRepository.getAvgAge();
    }

    public List<Student> getLastFive() {
        logger.info("Был вызван метод getLastFive");
        return studentRepository.getLastFive();
    }

    public List<Student> getByFacultyId(Long facultyId) {
        logger.info("Был вызван метод getByFacultyId");
        return studentRepository.findByFacultyId(facultyId);
    }

    public List<String> getAllWhereNameStartWithA() {
        String startSymbol = "A";
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name -> name.startsWith(startSymbol.toUpperCase()))
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAvgAgeWithStream() {
        return studentRepository.findAll().stream()
                .mapToDouble(student -> (double) student.getAge())
                .average()
                .orElse(0);
    }
}
