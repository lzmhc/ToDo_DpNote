package org.lzmhc.DpMenu;

import org.lzmhc.GuiLauncher;
import org.lzmhc.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

@Component
@Scope("prototype")
public class DpMenuBar extends JMenuBar{
    public JMenu addMenu = new DpMenu("新增");
    public JMenu editMenu = new DpMenu("编辑");
    public JMenu themeMenu = new DpMenu("主题", Arrays.asList(
            "FlatSolarizedDarkIJTheme",
            "FlatXcodeDarkIJTheme", "FlatArcDarkOrangeIJTheme",
            "FlatSpacegrayIJTheme", "FlatDarkFlatIJTheme",
            "FlatGradiantoDarkFuchsiaIJTheme",
            "FlatGradiantoNatureGreenIJTheme",
            "FlatGrayIJTheme",
            "FlatHighContrastIJTheme",
            "FlatMaterialDesignDarkIJTheme"));
    public DpMenuBar() {
        this.add(addMenu);
        this.add(editMenu);
        this.add(themeMenu);
    }
}
