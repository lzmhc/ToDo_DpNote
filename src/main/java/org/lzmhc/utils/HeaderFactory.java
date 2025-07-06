package org.lzmhc.utils;

import org.jdesktop.swingx.JXHeader;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HeaderFactory {
    public DpHeader createDefaultHeader(String title, String description) {
        return new DpHeader(title, description);
    }
    public static class DpHeader extends JXHeader {
        private final Date date;
        public DpHeader(String title, String description) {
            super(title, description);
            this.date = new Date();
        }
    }
}
