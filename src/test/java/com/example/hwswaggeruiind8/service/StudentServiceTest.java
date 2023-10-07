package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Test
    void delete() {

        when(studentRepository.findById(any())).then(any());
//        doNothing(studentRepository.deleteById(any()));

        verify(studentRepository, times(1)).deleteById(any());
        verifyNoInteractions(studentRepository);
        verifyNoMoreInteractions();
    }
}