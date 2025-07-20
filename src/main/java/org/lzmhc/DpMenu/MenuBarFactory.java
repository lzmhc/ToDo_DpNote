package org.lzmhc.DpMenu;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.DpMenu.AbstractMenuBuilder.PopupMenuBuilder;
import org.lzmhc.DpMenu.MenuBuilder.AddMenuBuilder;
import org.lzmhc.DpMenu.MenuBuilder.EditMenuBuilder;
import org.lzmhc.DpMenu.MenuBuilder.HelpMenuBuilder;
import org.lzmhc.DpMenu.MenuBuilder.ThemeMenuBuilder;
import org.lzmhc.DpMenu.PopupMenuBuilder.DelPopupMenuBuilder;
import org.lzmhc.DpMenu.PopupMenuBuilder.EditPopupMenuBuilder;
import org.lzmhc.DpMenu.PopupMenuBuilder.SavePopupMenuBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MenuBarFactory{
    // 菜单栏
    @Autowired
    private AddMenuBuilder addMenuBuilder;
    @Autowired
    private EditMenuBuilder editMenuBuilder;
    @Autowired
    private HelpMenuBuilder helpMenuBuilder;
    @Autowired
    private ThemeMenuBuilder themeMenuBuilder;
    // 右键菜单
    @Autowired
    private EditPopupMenuBuilder editPopupMenuBuilder;
    @Autowired
    private SavePopupMenuBuilder savePopupMenuBuilder;
    @Autowired
    private DelPopupMenuBuilder delPopupMenuBuilder;
    /**
     * 菜单栏
     * @param frame
     * @return
     */
    public JMenuBar createMenuBar(DpFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(addMenuBuilder.templateMenu(menuBar));
        editMenuBuilder.setDpFrame(frame);
        menuBar.add(editMenuBuilder.templateMenu(menuBar));
        menuBar.add(helpMenuBuilder.templateMenu(menuBar));
        menuBar.add(themeMenuBuilder.templateMenu(menuBar));
        return menuBar;
    }

    /**
     * 右键菜单
     * @param frame
     * @return
     */
    public JPopupMenu createPopupMenu(DpFrame frame) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(editPopupMenuBuilder.templateMenu(menu));
        editPopupMenuBuilder.setFrame(frame);
        menu.add(savePopupMenuBuilder.templateMenu(menu));
        savePopupMenuBuilder.setFrame(frame);
        menu.add(delPopupMenuBuilder.templateMenu(menu));
        delPopupMenuBuilder.setFrame(frame);
        return menu;
    }
}
