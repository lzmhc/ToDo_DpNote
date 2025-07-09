package org.lzmhc;

import jakarta.annotation.PostConstruct;
import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.entity.ToDo;
import org.lzmhc.service.NoteService;
import org.lzmhc.utils.WindowManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Component
public class GuiLauncher {
    @Autowired
    private WindowManager windowManager;
    @Autowired
    private NoteService noteService;
    @PostConstruct
    public void launch() {
        // 初始化数据库文件
//        String userHome = System.getProperty("user.home");
//        Path targetDbPath = Paths.get(userHome, ".todo", "todo.sqlite");
//        if (Files.notExists(targetDbPath.getParent())) {
//            try{
//                Files.createDirectories(targetDbPath.getParent());
//            }catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//        }
//        if (Files.notExists(targetDbPath)) {
//            ClassPathResource resource = new ClassPathResource("todo.sqlite");
//            if (resource.exists()) {
//                try{
//                    Files.copy(resource.getInputStream(), targetDbPath, StandardCopyOption.REPLACE_EXISTING);
//                    System.out.println("初始化数据库文件已复制到: " + targetDbPath.toAbsolutePath());
//                }catch (Exception e){
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                throw new RuntimeException("未找到资源文件: todo.sqlite");
//            }
//        }
        //主程序运行
        List<ToDo> toDoList = noteService.findAll();
        if(toDoList.size()>0){
            SwingUtilities.invokeLater(() -> {
                for(ToDo todo : toDoList){
                    windowManager.openNewNoteWindow(todo);
                }
            });
        }else{
            SwingUtilities.invokeLater(() -> {
                windowManager.openNewNoteWindow();
            });
        }
    }
}
