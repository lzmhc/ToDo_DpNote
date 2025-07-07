package org.lzmhc.DpMenu;

import org.lzmhc.utils.ThemeSwitcher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
@Scope("prototype")
public class DpMenuBar extends JMenuBar{
    public JMenu addMenu = new DpMenu("新增");
    public JMenu editMenu = new DpMenu("编辑");
    public JMenu themeMenu = new DpMenu("主题", ThemeSwitcher.menuItems);
    public JMenu helpMenu = new DpMenu("帮助");
    public DpMenuBar() {
        this.add(addMenu);
        this.add(editMenu);
        this.add(themeMenu);
        this.add(helpMenu);
        helpMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String htmlMessage = """
                <html>
                <h3>📘 快捷键说明</h3>
                <ul>
                    <li><b>Ctrl + E</b> —— 开启编辑模式</li>
                    <li><b>Ctrl + S</b> —— 保存并关闭编辑模式</li>
                    <li><b>Ctrl + O</b> —— 新建窗口</li>
                    <li><b>Ctrl + Q</b> —— 关闭当前窗口</li>
                </ul>
                </html>
        """;

                JOptionPane.showMessageDialog(DpMenuBar.this, htmlMessage, "快捷键说明", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
