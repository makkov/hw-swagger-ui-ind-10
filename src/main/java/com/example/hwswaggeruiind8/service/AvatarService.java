package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.enitity.Avatar;
import com.example.hwswaggeruiind8.enitity.Student;
import com.example.hwswaggeruiind8.repository.AvatarRepository;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private final String avatarsDir;


    public AvatarService(
            StudentRepository studentRepository,
            AvatarRepository avatarRepository,
            @Value("${path.to.avatars.folder}") String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.getById(studentId);
        // строчка ниже работает для MacOs. заменить для Windows: Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Path filePath = Path.of(new File("").getAbsolutePath() + avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        //Запись в БД
        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
