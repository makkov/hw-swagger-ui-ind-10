package com.example.hwswaggeruiind8.repository;

import com.example.hwswaggeruiind8.enitity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {


    List<Faculty> findByColorIgnoreCase(String color);

    List<Faculty> findByNameIgnoreCase(String name);
}
