package org.lzmhc.DpMenu.MenuBuilder;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.DpMenu.AbstractMenuBuilder.MenuBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 编辑菜单
 */
@Component
@Scope("prototype")
public class EditMenuBuilder extends MenuBuilder {
    private DpFrame dpFrame;

    public void setDpFrame(DpFrame dpFrame) {
        this.dpFrame = dpFrame;
    }
    @Override
    protected JMenu createMenu() {
        JMenu menu = new JMenu("编辑");
        // 编辑
        menu.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dpFrame.getEditorPane().setEditable(true);
                    }
                }
        );
        return menu;
    }

    @Override
    protected void addItem(JMenuBar menuBar) {
        menuBar.add(super.menu);
    }
}
