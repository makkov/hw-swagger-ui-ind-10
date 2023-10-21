package com.example.hwswaggeruiind8.controller;

import com.example.hwswaggeruiind8.enitity.Avatar;
import com.example.hwswaggeruiind8.service.AvatarService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/student/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatarFromDb(@PathVariable Long id) {
        return avatarService.downloadAvatarByStudentFromDb(id);
    }

    @GetMapping(value = "/student/{id}/avatar-from-file")
    public void downloadAvatarFromFileSystem(@PathVariable Long id, HttpServletResponse response) throws IOException{
        avatarService.downloadAvatarFromFileSystem(id, response);
    }

    @GetMapping
    public Page<Avatar> getWithPageable(@RequestParam Integer page, @RequestParam Integer count) throws IOException{
        return avatarService.getWithPageable(page, count);
    }
}
