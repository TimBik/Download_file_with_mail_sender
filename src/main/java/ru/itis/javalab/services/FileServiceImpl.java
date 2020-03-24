package ru.itis.javalab.services;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.dto.FileDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.repositories.FileRepository;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@PropertySource("classpath:properties/application.properties")
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private Environment environment;
//    @Autowired
//    final String rootPath;

    @Override
    public File findFile(MultipartFile fileMultiPart) {
        File dir = new File(environment.getProperty("storage.path")); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        String rand = RandomStringUtils.random(20, true, true);
        String allName = environment.getProperty("temporary.storage.path") + rand + ".my";
        try {
            fileMultiPart.transferTo(Paths.get(allName));
            File file = new File(allName);
            for (File f :
                    arrFiles) {
                if (f.compareTo(file) == 0) {
                    return f;
                }
            }
            file.delete();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    @Override
    public FileDto uploadFile(MultipartFile multipartFile, UserDto userDto) {
        //вынеси по методам эту фигню
        String rand = RandomStringUtils.random(20, true, true);
        //String rand = UUID.randomUUID().toString();
        String name = multipartFile.getOriginalFilename();
        String[] all = name.split("\\.");
        if (all.length < 2) {
            return null;
        }
        String ras = "." + all[all.length - 1];
        String allName = environment.getProperty("storage.path") + rand + ras;
        try {
            multipartFile.transferTo(Paths.get(allName));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return new FileDto().builder().
                name(rand + ras).
                userId(userDto.getId()).
                userMail(userDto.getEmail()).
                build();
        //FileDto fileDto = FileDto.builder().name().build();
        //fileRepository.save(fileDto);
    }

    @Override
    public File findFile(String fileName) {
        String path = environment.getProperty("storage.path") + fileName;
        //Path file = Paths.get(PathUtil.getUploadedFolder(), fileName);
        //if (Files.exists(path)){
        return new File(path);
    }
}
