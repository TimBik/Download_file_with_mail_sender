package ru.itis.javalab.services;

import ru.itis.javalab.dto.FileDto;

public interface DocumentService {
    void saveByFileDto(FileDto fileDto, String allPath);

}
