package com.example.hwswaggeruiind8.repository;

import com.example.hwswaggeruiind8.enitity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);
}
