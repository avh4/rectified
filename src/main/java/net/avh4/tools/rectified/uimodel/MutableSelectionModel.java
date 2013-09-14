package net.avh4.tools.rectified.uimodel;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Group;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.Observable;

public class MutableSelectionModel extends Observable<SelectionQuery> implements SelectionCommands {
    private SelectionQueryImpl selectionQuery;

    @Override public void selectComponent(Group parent, Component component) {
        System.out.println("Selected " + component);
        selectionQuery = new SelectionQueryImpl(parent, component);
        notifyObservers();
    }

    @Override protected SelectionQuery getObservedValue() {
        return selectionQuery;
    }

    private static class SelectionQueryImpl implements SelectionQuery {
        private final Group parentOfSelected;
        private final Component selectedComponent;

        public SelectionQueryImpl(Group parentOfSelected, Component selectedComponent) {
            this.parentOfSelected = parentOfSelected;
            this.selectedComponent = selectedComponent;
        }

        @Override public Component selectedComponent() {
            return selectedComponent;
        }

        @Override public Group parentOfSelected() {
            return parentOfSelected;
        }
    }
}
