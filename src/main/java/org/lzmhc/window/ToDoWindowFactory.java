package org.lzmhc.window;

import org.lzmhc.DpFrame.DpFrame;
import org.lzmhc.entity.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ToDoWindowFactory implements WindowFactory {
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public DpFrame openNewNoteWindow() {
        return applicationContext.getBean(DpFrame.class);
    }
    @Override
    public DpFrame openNewNoteWindow(ToDo toDo) {
        DpFrame frame = applicationContext.getBean(DpFrame.class);
        frame.setUuid(toDo.getId());
        frame.createAndShowGUI(toDo.getContent());
        return frame;
    }
}
