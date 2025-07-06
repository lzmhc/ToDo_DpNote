package org.lzmhc.utils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class ThemeSwitcher {
    /**
     * 根据主题类名切换 FlatLaf 主题
     */
    public static boolean setTheme(String themeClassName) {
        try {
            // 拼接完整类名
            String fullClassName = "com.formdev.flatlaf.intellijthemes." + themeClassName;
            Class<?> themeClass = Class.forName(fullClassName);
            // 查找 setup() 静态方法
            Method setupMethod = themeClass.getMethod("setup");
            // 执行 setup()
            setupMethod.invoke(null); // 静态方法传 null
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 更新所有组件的 UI（可选）
     */
    public static void updateComponentTree(Component component) {
        SwingUtilities.updateComponentTreeUI(component);
    }
}
