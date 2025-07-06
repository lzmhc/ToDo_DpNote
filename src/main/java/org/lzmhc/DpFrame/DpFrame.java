package org.lzmhc.DpFrame;

import org.jdesktop.swingx.JXEditorPane;
import org.lzmhc.GuiLauncher;
import org.lzmhc.utils.DpMenuBarFactory;
import org.lzmhc.utils.HeaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
@Component
@Scope("prototype")
public class DpFrame extends JFrame {
    @Autowired
    private DpMenuBarFactory menuBarFactory;
    public int frameId = GuiLauncher.FrameNum;
    private JXEditorPane editorPane = new JXEditorPane("text/html; charset=UTF-8", "<h3>#ToDo "+frameId+"</h3><hr>");
    public void createAndShowGUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setJMenuBar(menuBarFactory.createMenuBar());
        editorPane.setEditable(false);
        this.add(editorPane);
        // 显示窗口
        this.pack();
        this.setSize(520,220);
        this.setResizable(false);
        setLocationToTopRight(this);
        this.setVisible(true);
    }
    public static void setLocationToTopRight(Window window) {
        Dimension windowSize = window.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - windowSize.width;
        int y = GuiLauncher.FrameNum * windowSize.height;
        window.setLocation(x, y);
    }
}