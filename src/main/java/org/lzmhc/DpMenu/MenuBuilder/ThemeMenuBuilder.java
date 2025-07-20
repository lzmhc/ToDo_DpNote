package org.lzmhc.DpMenu.MenuBuilder;

import org.lzmhc.DpMenu.AbstractMenuBuilder.MenuBuilder;
import org.lzmhc.utils.ThemeSwitcher;
import org.springframework.context.annotation.Scope;

import javax.swing.*;
import java.awt.*;

/**
 * 主题菜单
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class ThemeMenuBuilder extends MenuBuilder {
    @Override
    protected JMenu createMenu() {
        JMenu menu = new  JMenu("主题");
        for(String item : ThemeSwitcher.menuItems){
            JMenuItem menuItem = new JMenuItem(item);
            menu.add(menuItem);
            menuItem.addActionListener(e -> {
                String selectedTheme = menuItem.getText();
                // 切换主题
                if (ThemeSwitcher.setTheme(selectedTheme)) {
                    Component root = SwingUtilities.getWindowAncestor(menu);
                    if (root != null) {
                        ThemeSwitcher.updateComponentTree(root);
                    }
                } else {
                    JOptionPane.showMessageDialog(menu,
                            "无法加载主题: " + selectedTheme,
                            "错误",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        return menu;
    }
    @Override
    protected void addItem(JMenuBar menuBar) {
        menuBar.add(super.menu);
    }
}
