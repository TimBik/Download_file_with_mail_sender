package ru.itis.javalab.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    void uploadFile(MultipartFile multipartFile);

    File findFile(String fileName);
}
