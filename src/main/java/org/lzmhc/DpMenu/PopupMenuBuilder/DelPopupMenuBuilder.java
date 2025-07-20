package org.lzmhc.DpMenu.PopupMenuBuilder;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.DpMenu.AbstractMenuBuilder.PopupMenuBuilder;
import org.lzmhc.utils.WindowManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * 右键删除菜单
 */
@Component
@Scope("prototype")
public class DelPopupMenuBuilder extends PopupMenuBuilder {
    private DpFrame frame;

    public void setFrame(DpFrame frame) {
        this.frame = frame;
    }
    @Override
    protected JMenuItem createMenu() {
        JMenuItem menu = new JMenuItem("删除");
        menu.addActionListener(e -> {
            frame.getNoteService().deleteNoteById(frame.getUuid());
            WindowManager.removeNoteWindow(frame);
        });
        return menu;
    }

    @Override
    protected void addItem(JPopupMenu popupMenu) {
        popupMenu.add(super.menu);
    }
}
