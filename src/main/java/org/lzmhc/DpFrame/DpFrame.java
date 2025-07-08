package org.lzmhc.DpFrame;

import org.jdesktop.swingx.JXEditorPane;
import org.lzmhc.DpMenu.DpMenuBar;
import org.lzmhc.DpMenu.DpPopupMenu;
import org.lzmhc.entity.ToDo;
import org.lzmhc.repository.NotesRepository;
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
    private String content;
    @Autowired
    private WindowManager windowManager;
    @Autowired
    private DpMenuBar menuBar;
    @Autowired
    private DpPopupMenu popupMenu;
    @Autowired
    private NoteService noteService;

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private JXEditorPane editorPane = new JXEditorPane("text/html; charset=UTF-8", content);
    public void createAndShowGUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setJMenuBar(menuBar);
        // 新建窗口
        menuBar.addMenu.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        windowManager.openNewNoteWindow();
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
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        popupMenu.editMenu.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        editorPane.setEditable(true);
                    }
                }
        );
        popupMenu.saveMenu.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        editorPane.setEditable(false);
                        String text = editorPane.getText();
                        ToDo note = new ToDo();
                        note.setId(uuid);
                        note.setContent(text);
                        noteService.save(note);
                    }
                }
        );
        popupMenu.delMenu.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        noteService.deleteNoteById(uuid);
                        WindowManager.removeNoteWindow(DpFrame.this);
                        DpFrame.this.dispose();
                        if (WindowManager.FrameNum < 0) {
                            System.exit(0);
                        }
                    }
                }
        );
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
//        this.setResizable(false);
        this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4)));
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.pack();
        setLocationToTopRight(this);
        this.setVisible(true);
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