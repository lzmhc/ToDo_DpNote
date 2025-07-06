package org.lzmhc.service;

import org.lzmhc.DpFrame.DpFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {
    public static List<DpFrame> dpFrameList = new ArrayList<>() ;
    @Autowired
    private ApplicationContext context;
    public void openNewNoteWindow() {
        SwingUtilities.invokeLater(() -> {
            DpFrame frame = context.getBean(DpFrame.class);
            frame.createAndShowGUI();
            dpFrameList.add(frame);
        });
    }
}
