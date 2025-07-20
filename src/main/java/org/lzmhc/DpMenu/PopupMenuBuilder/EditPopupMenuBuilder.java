package org.lzmhc.DpMenu.PopupMenuBuilder;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.DpMenu.AbstractMenuBuilder.PopupMenuBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * 右键编辑菜单
 */
@Component
@Scope("prototype")
public class EditPopupMenuBuilder extends PopupMenuBuilder {
    private DpFrame frame;

    public void setFrame(DpFrame frame) {
        this.frame = frame;
    }

    @Override
    protected JMenuItem createMenu() {
        JMenuItem menu = new JMenuItem("编辑");
        menu.addActionListener(e -> {
            frame.getEditorPane().setEditable(true);
        });
        return menu;
    }
    @Override
    protected void addItem(JPopupMenu popupMenu) {
        popupMenu.add(super.menu);
    }
}
