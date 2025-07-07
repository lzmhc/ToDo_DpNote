package org.lzmhc.DpMenu;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
@Component
@Scope("prototype")
public class DpPopupMenu extends JPopupMenu {
    public JMenuItem editMenu = new JMenuItem("编辑");
    public JMenuItem saveMenu = new JMenuItem("保存");
    DpPopupMenu() {
        this.add(editMenu);
        this.add(saveMenu);
    }
}
