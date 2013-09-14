package net.avh4.tools.rectified.uimodel.cqrs;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Group;

public interface SelectionCommands {
    void selectComponent(Group parent, Component component);
}
