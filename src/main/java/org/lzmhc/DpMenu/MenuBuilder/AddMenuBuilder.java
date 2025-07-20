package org.lzmhc.DpMenu.MenuBuilder;

import org.lzmhc.DpMenu.AbstractMenuBuilder.MenuBuilder;
import org.lzmhc.utils.WindowManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 新增菜单
 */
@Component
@Scope("prototype")
public class AddMenuBuilder extends MenuBuilder {
    @Autowired
    private WindowManager windowManager;
    @Override
    protected JMenu createMenu() {
        JMenu menu = new JMenu("新增");
        // 新建窗口
        menu.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        windowManager.openNewNoteWindow();
                    }
                }
            }
        );
        return  menu;
    }

    @Override
    protected void addItem(JMenuBar menuBar) {
        menuBar.add(super.menu);
    }
}
