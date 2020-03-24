package ru.itis.javalab.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.dto.FileDto;
import ru.itis.javalab.dto.UserDto;

import javax.servlet.http.HttpSession;
import java.io.File;

public interface FileService {
    FileDto uploadFile(MultipartFile multipartFile, UserDto userDto);

    File findFile(String fileName);

    File findFile(MultipartFile file);
}
