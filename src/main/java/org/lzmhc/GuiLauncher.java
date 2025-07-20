package org.lzmhc;

import jakarta.annotation.PostConstruct;
import org.lzmhc.entity.ToDo;
import org.lzmhc.service.NoteService;
import org.lzmhc.utils.WindowManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class GuiLauncher {
    @Autowired
    private WindowManager windowManager;
    @Autowired
    private NoteService noteService;
    @PostConstruct
    public void launch() {
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
