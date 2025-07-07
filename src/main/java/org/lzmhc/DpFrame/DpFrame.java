package org.lzmhc.DpFrame;

import org.jdesktop.swingx.JXEditorPane;
import org.lzmhc.DpMenu.DpMenuBar;
import org.lzmhc.GuiLauncher;
import org.lzmhc.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Component
@Scope("prototype")
public class DpFrame extends JFrame {
    @Autowired
    private ToDoService toDoService;
    @Autowired
    private DpMenuBar menuBar;
    public int frameId = GuiLauncher.FrameNum;
    private JXEditorPane editorPane = new JXEditorPane("text/html; charset=UTF-8", "<h3>#ToDo</h3><hr>");
    public void createAndShowGUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setJMenuBar(menuBar);
        // 新建窗口
        menuBar.addMenu.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        toDoService.openNewNoteWindow();
                        GuiLauncher.FrameNum += 1;
                    }
                }
            }
        );
        // 编辑
        menuBar.editMenu.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        editorPane.setEditable(true);
                    }
                }
        );
        editorPane.setEditable(false);
        this.add(editorPane);
        // 获取 InputMap 和 ActionMap
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        // 定义 Ctrl+E 动作
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK), "editAction");
        actionMap.put("editAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPane.setEditable(true);
            }
        });

        // 定义 Ctrl+S 动作
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "saveAction");
        actionMap.put("saveAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPane.setEditable(false);
            }
        });

        // 显示窗口
        this.pack();
        this.setResizable(false);
        this.setSize(720,320);
        this.setMaximumSize(new Dimension(720,420));
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