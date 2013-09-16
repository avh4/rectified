package net.avh4.tools.rectified.uimodel.cqrs;

import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.Component;
import org.pcollections.PStack;

public interface SelectionQuery {
    Component selectedComponent();

    PStack<Component> path();

    Rect selectionBounds();
}
