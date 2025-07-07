package org.lzmhc;

import jakarta.annotation.PostConstruct;
import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.utils.WindowManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class GuiLauncher {
    @Autowired
    private DpFrame frame;
    @PostConstruct
    public void launch() {
        SwingUtilities.invokeLater(() -> {
            frame.createAndShowGUI();
            WindowManager.FrameNum++;
            WindowManager.openFrames.add(frame);
        });
    }
}
