package ru.itis.javalab.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.FileDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.MailService;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class FilePathSender {

    @Autowired
    private MailService mailService;


    @AfterReturning(
            pointcut = "execution(* ru.itis.javalab.services.FileService.uploadFile(*,*))",
            returning = "fileDtoObject")
    public void after(JoinPoint joinPoint, Object fileDtoObject) {
        Object[] args = joinPoint.getArgs();
        Map<String, Object> root = new HashMap<>();
        UserDto user = (UserDto) args[1];
        FileDto fileDto = (FileDto) fileDtoObject;
        if (user != null) {
            root.put("user", user);
            root.put("file", "files/" + fileDto.getName());
            mailService.sendMessage("File info", user.getEmail(), root, "mail_file.ftl");
        }
    }

//    @Pointcut("execution(* ru.itis.javalab.services.FileService.uploadFile(*))")
//    public void afterUpload() {
//    }
//
//    @Pointcut("execution(* ru.itis.javalab.controllers.FilesController.uploadFile(*, *))")
//    public void afterController() {
//    }

//    @AfterReturning(
//            pointcut = "",
//            returning = "path")
//    public String getName(Object path) {
//        return;
//    }
}

