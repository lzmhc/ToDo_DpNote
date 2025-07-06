package org.lzmhc.DpMenu;

import org.lzmhc.utils.ThemeSwitcher;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DpMenu extends JMenu {
    private List<String> menuItemList = new ArrayList<>();
    public DpMenu(String name){
        super(name);
    }
    public DpMenu(String name, List<String> menuItemList) {
        super(name);
        this.menuItemList = menuItemList;
        for(String item:menuItemList){
            JMenuItem menuItem = new JMenuItem(item);
            this.add(menuItem);
            menuItem.addActionListener(e -> {
                String selectedTheme = menuItem.getText();
                // 切换主题
                if (ThemeSwitcher.setTheme(selectedTheme)) {
                    Component root = SwingUtilities.getWindowAncestor(DpMenu.this);
                    if (root != null) {
                        ThemeSwitcher.updateComponentTree(root);
                    }
                } else {
                    JOptionPane.showMessageDialog(DpMenu.this,
                            "无法加载主题: " + selectedTheme,
                            "错误",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}
