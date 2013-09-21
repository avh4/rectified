package net.avh4.tools.rectified.uimodel;

import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Group;
import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import org.pcollections.PStack;

public class MutableSelectionModel extends Channel<SelectionQuery> implements SelectionCommands {
    private SelectionQueryImpl selectionQuery;

    @Override public void selectComponent(PStack<Component> path) {
        Rect selectedBounds = calculateBounds(path);
        selectionQuery = new SelectionQueryImpl(path, selectedBounds);
        notifyObservers();
    }

    private Rect calculateBounds(PStack<Component> path) {
        Rect selectedBounds = Rect.ofSize(320, 508);
        Component[] components = new Component[path.size()];
        int i = components.length;
        for (Component component : path) {
            components[--i] = component;
        }
        for (int j = 0; j < components.length - 1; j++) {
            Group parent = (Group) components[j];
            selectedBounds = parent.placedBoundsForChild(selectedBounds, components[j + 1]);
        }
        return selectedBounds;
    }

    @Override public SelectionQuery get() {
        return selectionQuery;
    }

    private static class SelectionQueryImpl implements SelectionQuery {
        private final PStack<Component> path;
        private final Rect selectionBounds;

        public SelectionQueryImpl(PStack<Component> path, Rect selectionBounds) {
            this.path = path;
            this.selectionBounds = selectionBounds;
        }

        @Override public Component selectedComponent() {
            return path.get(0);
        }

        @Override public PStack<Component> path() {
            return path;
        }

        @Override public Rect selectionBounds() {
            return selectionBounds;
        }
    }
}
