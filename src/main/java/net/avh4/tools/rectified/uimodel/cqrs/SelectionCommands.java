package net.avh4.tools.rectified.uimodel.cqrs;

import net.avh4.tools.rectified.model.Component;
import org.pcollections.PStack;

public interface SelectionCommands {
    void selectComponent(PStack<Component> path);
}
