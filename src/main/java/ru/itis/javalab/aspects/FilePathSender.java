package ru.itis.javalab.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.FileService;
import ru.itis.javalab.services.MailService;
import ru.itis.javalab.services.MailServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class FilePathSender {

    @Autowired
    private MailService mailService;


    @After(value = "execution(* ru.itis.javalab.controllers.FilesController.uploadFile(*, *))")
    public void after(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MultipartFile filename = (MultipartFile) args[0];
        Map<String, Object> root = new HashMap<>();
        HttpSession session = (HttpSession) args[1];
        UserDto user = (UserDto) session.getAttribute("current_user");
        if (user != null) {
            root.put("user", user);
            root.put("file", filename.getSize());
            mailService.sendMessage("File info", user.getEmail(), root, "mail_file.ftl");
        }
    }
}

