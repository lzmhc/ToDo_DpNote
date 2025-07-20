package org.lzmhc.window;

import org.lzmhc.entity.ToDo;
import javax.swing.*;

public interface WindowFactory {
    JFrame openNewNoteWindow();
    JFrame openNewNoteWindow(ToDo toDo);
}
