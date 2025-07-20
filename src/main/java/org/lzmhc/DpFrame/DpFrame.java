package org.lzmhc.DpFrame;

import org.jdesktop.swingx.JXEditorPane;
import org.lzmhc.DpMenu.MenuBarFactory;
import org.lzmhc.entity.ToDo;
import org.lzmhc.service.NoteService;
import org.lzmhc.utils.WindowManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Component
@Scope("prototype")
public class DpFrame extends JFrame {
    private String uuid;
    @Autowired
    private MenuBarFactory menuBarFactory;
    @Autowired
    private WindowManager windowManager;
    @Autowired
    private NoteService noteService;
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public NoteService getNoteService() {
        return noteService;
    }

    private JXEditorPane editorPane = new JXEditorPane("text/html; charset=UTF-8", "");

    public JXEditorPane getEditorPane() {
        return editorPane;
    }

    public void createAndShowGUI(String content) {
        editorPane.setText(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setJMenuBar(menuBarFactory.createMenuBar(this));
        editorPane.setEditable(false);
        // 右键菜单
        editorPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }
            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    menuBarFactory.createPopupMenu(DpFrame.this).show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
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
                String text = editorPane.getText();
                ToDo note = new ToDo();
                note.setId(uuid);
                note.setContent(text);
                noteService.save(note);
            }
        });
        // 定义 Ctrl+O 动作
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "newAction");
        actionMap.put("newAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowManager.openNewNoteWindow();
            }
        });
        // 定义 Ctrl+Q 动作
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK), "quitAction");
        actionMap.put("quitAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭当前窗口
                WindowManager.removeNoteWindow(DpFrame.this);
                DpFrame.this.dispose();
                if (WindowManager.FrameNum < 0) {
                    System.exit(0);
                }
            }
        });
        // 显示窗口
        this.setResizable(false);
        this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4)));
        //去掉最小化/最大化/关闭按钮
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.pack();
        WindowManager.setLocationToTopRight(this);
        this.setVisible(true);
    }

}