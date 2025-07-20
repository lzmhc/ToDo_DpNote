package org.lzmhc.DpMenu.PopupMenuBuilder;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.DpMenu.AbstractMenuBuilder.PopupMenuBuilder;
import org.lzmhc.entity.ToDo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.UUID;

/**
 * 右键保存菜单
 */
@Component
@Scope("prototype")
public class SavePopupMenuBuilder extends PopupMenuBuilder {
    private DpFrame frame;

    public void setFrame(DpFrame frame) {
        this.frame = frame;
    }
    @Override
    protected JMenuItem createMenu() {
        JMenuItem menu = new JMenuItem("保存");
        menu.addActionListener(e -> {
            frame.getEditorPane().setEditable(false);
            String text = frame.getEditorPane().getText();
            ToDo note = new ToDo();
            note.setId(frame.getUuid());
            note.setContent(text);
            frame.getNoteService().save(note);
        });
        return menu;
    }

    @Override
    protected void addItem(JPopupMenu popupMenu) {
        popupMenu.add(super.menu);
    }
}
