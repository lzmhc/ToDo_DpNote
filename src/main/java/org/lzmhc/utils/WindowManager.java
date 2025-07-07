package org.lzmhc.utils;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.DpNoteApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class WindowManager {
    public static int FrameNum = 0;
    public static List<JFrame> openFrames = new ArrayList<>();
    @Autowired
    private ApplicationContext context;
    public void openNewNoteWindow() {
        SwingUtilities.invokeLater(() -> {
            DpFrame frame = context.getBean(DpFrame.class);
            frame.createAndShowGUI();
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
