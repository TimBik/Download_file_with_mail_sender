package ru.itis.javalab.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.FileDto;
import ru.itis.javalab.model.Document;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.DocumentRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    UsersService userService;

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public void saveByFileDto(FileDto fileDto, String allPath) {
        User user = userService.find(fileDto.getUserId());
        try (Stream<Path> filesPaths = Files.walk(Paths.get(allPath))) {
            filesPaths.filter(filePath -> filePath.toFile().isFile()).forEach(
                    filePath -> {
                        File file = filePath.toFile();
                        Document document = null;
                        document = Document.builder()
                                .owner(user)
                                .path(filePath.toString())
                                .size(file.length())
                                .type(FilenameUtils.getExtension(allPath))
                                .pathForDownload(fileDto.getName())
                                .build();

                        documentRepository.save(document);
                    }
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


    }
}
