package com.example.hwswaggeruiind8.repository;

import com.example.hwswaggeruiind8.enitity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAgeBetween(int min, int max);

    List<Student> findByFacultyId(Long facultyId);
}
