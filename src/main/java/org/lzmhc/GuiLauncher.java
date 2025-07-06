package org.lzmhc;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import com.formdev.flatlaf.util.SystemInfo;
import jakarta.annotation.PostConstruct;
import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class GuiLauncher {
    public static int FrameNum = 0;
    @Autowired
    private DpFrame frame;
    @PostConstruct
    public void launch() {
        SwingUtilities.invokeLater(() -> {
            frame.createAndShowGUI();
            ToDoService.dpFrameList.add(frame);
        });
    }
}
