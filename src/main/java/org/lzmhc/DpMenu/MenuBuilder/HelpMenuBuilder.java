package org.lzmhc.DpMenu.MenuBuilder;

import org.lzmhc.DpMenu.AbstractMenuBuilder.MenuBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 帮助菜单
 */
@Component
@Scope("prototype")
public class HelpMenuBuilder extends MenuBuilder {
    @Override
    protected JMenu createMenu() {
        JMenu helpMenu = new JMenu("帮助");
        helpMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String htmlMessage = """
                <html>
                <h3>快捷键说明</h3>
                <ul>
                    <li>Ctrl+E 开启编辑模式</li>
                    <li>Ctrl+S 保存并关闭编辑模式</li>
                    <li>Ctrl+O 新建窗口</li>
                    <li>Ctrl+Q 关闭当前窗口</li>
                </ul>
                </html>
        """;
                JOptionPane.showMessageDialog(helpMenu, htmlMessage, "快捷键", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return  helpMenu;
    }

    @Override
    protected void addItem(JMenuBar menuBar) {
        menuBar.add(super.menu);
    }
}
