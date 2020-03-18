package ru.itis.javalab.services;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.dto.FileDto;
import ru.itis.javalab.repositories.FileRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@PropertySource("classpath:properties/application.properties")
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private Environment environment;
//    @Autowired
//    final String rootPath;

    @Override
    public void uploadFile(MultipartFile multipartFile) {
        String rand = RandomStringUtils.random(20, true, true);
        String name = multipartFile.getOriginalFilename();
        String[] all = name.split("\\.");
        try {
            String ras = "";
            if (all.length > 1) {
                ras = "." + all[all.length - 1];
            }
            String allName = environment.getProperty("storage.path") + rand + ras;

            multipartFile.transferTo(Paths.get(allName));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FileDto fileDto = FileDto.builder().name(name).build();
//        fileRepository.save(fileDto);
    }

    @Override
    public File findFile(String fileName) {
        String path = environment.getProperty("storage.path") + fileName;
        //Path file = Paths.get(PathUtil.getUploadedFolder(), fileName);
        //if (Files.exists(path)){
        return new File(path);
    }
}
