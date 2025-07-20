package org.lzmhc.utils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 主题切换工具
 */
public class ThemeSwitcher {
    public static ArrayList<String> menuItems = new ArrayList<>(
            Arrays.asList("FlatSolarizedDarkIJTheme",
                        "FlatXcodeDarkIJTheme", "FlatArcDarkOrangeIJTheme",
                        "FlatSpacegrayIJTheme", "FlatDarkFlatIJTheme",
                        "FlatGradiantoDarkFuchsiaIJTheme",
                        "FlatGradiantoNatureGreenIJTheme",
                        "FlatGrayIJTheme",
                        "FlatHighContrastIJTheme",
                        "FlatMaterialDesignDarkIJTheme"));
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
     * 更新所有组件的 UI
     */
    public static void updateComponentTree(Component component) {
        SwingUtilities.updateComponentTreeUI(component);
    }
}
