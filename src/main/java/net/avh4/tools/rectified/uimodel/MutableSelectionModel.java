package net.avh4.tools.rectified.uimodel;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.Observable;
import org.pcollections.PStack;

public class MutableSelectionModel extends Observable<SelectionQuery> implements SelectionCommands {
    private SelectionQueryImpl selectionQuery;

    @Override public void selectComponent(PStack<Component> path) {
        selectionQuery = new SelectionQueryImpl(path);
        notifyObservers();
    }

    @Override protected SelectionQuery getObservedValue() {
        return selectionQuery;
    }

    private static class SelectionQueryImpl implements SelectionQuery {
        private final PStack<Component> path;

        public SelectionQueryImpl(PStack<Component> path) {
            this.path = path;
        }

        @Override public Component selectedComponent() {
            return path.get(0);
        }

        @Override public PStack<Component> path() {
            return path;
        }
    }
}
