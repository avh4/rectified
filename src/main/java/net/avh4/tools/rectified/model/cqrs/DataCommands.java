package net.avh4.tools.rectified.model.cqrs;

import net.avh4.tools.rectified.model.Component;
import org.pcollections.PStack;

public interface DataCommands {

    PStack<Component> replace(PStack<Component> path, Component newComponent);

    void add(PStack<Component> path, Component component);
}
