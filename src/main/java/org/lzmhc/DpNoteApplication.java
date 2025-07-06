package org.lzmhc;

import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.util.SystemInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

@SpringBootApplication
public class DpNoteApplication {
    public static void main(String[] args) {
        // 菜单显示在标题栏
        if ( SystemInfo.isLinux ) {
            JFrame.setDefaultLookAndFeelDecorated( true );
            JDialog.setDefaultLookAndFeelDecorated( true );
        }
        FlatSolarizedDarkIJTheme.setup();
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(DpNoteApplication.class,args);
    }
}
