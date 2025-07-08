package org.lzmhc.utils;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.entity.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class WindowManager {
    public static int FrameNum = 0;
    public static List<JFrame> openFrames = new ArrayList<>();
    @Autowired
    private ApplicationContext context;
    public void openNewNoteWindow() {
        SwingUtilities.invokeLater(() -> {
            DpFrame frame = context.getBean(DpFrame.class);
            String uuid = UUID.randomUUID().toString();
            frame.setUuid(uuid);
            frame.createAndShowGUI("#ToDo");
            FrameNum+=1;
            openFrames.add(frame);
        });
    }
    public void openNewNoteWindow(ToDo toDo) {
        SwingUtilities.invokeLater(() -> {
            DpFrame frame = context.getBean(DpFrame.class);
            frame.setUuid(toDo.getId());
            frame.createAndShowGUI(toDo.getContent());
            FrameNum+=1;
            openFrames.add(frame);
        });
    }
    public static void removeNoteWindow(DpFrame frame) {
        SwingUtilities.invokeLater(() -> {
            FrameNum-=1;
            openFrames.remove(frame);
        });
    }
}
