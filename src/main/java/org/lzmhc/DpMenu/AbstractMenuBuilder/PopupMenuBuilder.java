package org.lzmhc.DpMenu.AbstractMenuBuilder;

import javax.swing.*;

public abstract class PopupMenuBuilder {
    protected JMenuItem menu ;
    public final JMenuItem templateMenu(JPopupMenu popupMenu){
        menu = createMenu();
        addItem(popupMenu);
        return menu;
    }
    protected abstract JMenuItem createMenu();
    protected abstract void addItem(JPopupMenu popupMenu);
}
