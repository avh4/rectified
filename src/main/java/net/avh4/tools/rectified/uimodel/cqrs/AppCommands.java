package net.avh4.tools.rectified.uimodel.cqrs;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import org.pcollections.PStack;

public class AppCommands {
    private final DataCommands dataCommands;
    private final SelectionCommands selectionCommands;

    public AppCommands(DataCommands dataCommands, SelectionCommands selectionCommands) {
        this.dataCommands = dataCommands;
        this.selectionCommands = selectionCommands;
    }

    public void replace(PStack<Component> path, Component newComponent) {
        final PStack<Component> newPath = dataCommands.replace(path, newComponent);
        selectionCommands.selectComponent(newPath);
    }

    public void add(PStack<Component> path, Component component) {
        final PStack<Component> newPath = dataCommands.add(path, component);
        selectionCommands.selectComponent(newPath);
    }
}
