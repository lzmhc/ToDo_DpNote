package org.lzmhc.utils;

import org.lzmhc.DpMenu.DpMenu;
import org.lzmhc.GuiLauncher;
import org.lzmhc.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

@Component
public class DpMenuBarFactory {
    @Autowired
    private ToDoService toDoService;
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // 新增菜单
        JMenu addMenu = new DpMenu("新增");
        addMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    toDoService.openNewNoteWindow();
                    GuiLauncher.FrameNum += 1;
                }
            }
        });

        // 编辑菜单
        JMenu editMenu = new DpMenu("编辑");
        editMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {

                }
            }
        });

        // 主题菜单
        JMenu themeMenu =  new DpMenu("主题", Arrays.asList(
                "FlatSolarizedDarkIJTheme",
                "FlatXcodeDarkIJTheme", "FlatArcDarkOrangeIJTheme",
                "FlatSpacegrayIJTheme", "FlatDarkFlatIJTheme",
                "FlatGradiantoDarkFuchsiaIJTheme",
                "FlatGradiantoNatureGreenIJTheme",
                "FlatGrayIJTheme",
                "FlatHighContrastIJTheme",
                "FlatMaterialDesignDarkIJTheme"));
        menuBar.add(addMenu);
        menuBar.add(editMenu);
        menuBar.add(themeMenu);
        return menuBar;
    }
}
