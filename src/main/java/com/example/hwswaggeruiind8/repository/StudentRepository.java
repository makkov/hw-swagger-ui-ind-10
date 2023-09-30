package com.example.hwswaggeruiind8.repository;

import com.example.hwswaggeruiind8.enitity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
