package org.lzmhc.DpMenu.AbstractMenuBuilder;

import javax.swing.*;

/**
 * 菜单项
 */
public abstract class MenuBuilder {
    protected JMenu menu ;
    public final JMenu templateMenu(JMenuBar menuBar){
        menu = createMenu();
        addItem(menuBar);
        return menu;
    }
    protected abstract JMenu createMenu();
    protected abstract void addItem(JMenuBar menuBar);
}
