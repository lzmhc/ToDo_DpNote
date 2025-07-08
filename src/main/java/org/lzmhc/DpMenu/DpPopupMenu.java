package org.lzmhc.DpMenu;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
@Component
@Scope("prototype")
public class DpPopupMenu extends JPopupMenu {
    public JMenuItem editMenu = new JMenuItem("编辑");
    public JMenuItem saveMenu = new JMenuItem("保存");
    public JMenuItem delMenu = new  JMenuItem("删除");
    DpPopupMenu() {
        this.add(editMenu);
        this.add(saveMenu);
        this.add(delMenu);
    }
}
