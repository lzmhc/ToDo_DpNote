package org.lzmhc.utils;

import jakarta.annotation.PostConstruct;
import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.entity.ToDo;
import org.lzmhc.window.ToDoWindowFactory;
import org.lzmhc.window.WindowFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
@Component
@Scope("singleton")
public class WindowManager {
    public static int FrameNum = 0;
    public static List<JFrame> openFrames = Collections.synchronizedList(new ArrayList<>());
    @Autowired
    private WindowFactory windowFactory;
    public void openNewNoteWindow() {
        SwingUtilities.invokeLater(() -> {
            DpFrame frame = (DpFrame) windowFactory.openNewNoteWindow();
            String uuid = UUID.randomUUID().toString();
            frame.setUuid(uuid);
            frame.createAndShowGUI("#ToDo");
            FrameNum+=1;
            openFrames.add(frame);
        });
    }
    public void openNewNoteWindow(ToDo toDo) {
        SwingUtilities.invokeLater(() -> {
            DpFrame frame = (DpFrame) windowFactory.openNewNoteWindow(toDo);
            frame.setUuid(toDo.getId());
            frame.createAndShowGUI(toDo.getContent());
            FrameNum+=1;
            openFrames.add(frame);
        });
    }
    public static void removeNoteWindow(DpFrame frame) {
        SwingUtilities.invokeLater(() -> {
            FrameNum-=1;
            frame.dispose();
            openFrames.remove(frame);
            if (WindowManager.FrameNum < 0) {
                System.exit(0);
            }
        });
    }
    public static void setLocationToTopRight(Window window) {
        Dimension windowSize = window.getSize(); // 窗口大小（建议设置固定值）
        int frameWidth = (int) windowSize.getWidth();
        int frameHeight = (int) windowSize.getHeight();
        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int index = WindowManager.FrameNum;
        int maxPerColumn = screenHeight / frameHeight;
        int col = index / maxPerColumn;
        int row = index % maxPerColumn;
        int columnGroup = col / 1;
        int x;
        x = screenWidth - (columnGroup + 1) * frameWidth - (columnGroup + 1);
        int y = row * frameHeight;
        window.setLocation(x, y);
    }
}
