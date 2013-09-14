package net.avh4.tools.rectified.uimodel.cqrs;

import net.avh4.tools.rectified.Observables;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import net.avh4.util.Observer;

public class SelectedComponentCommands {
    private final DataCommands dataModel;
    private final SelectionCommands selectionCommands;
    private SelectionQuery selectionQuery;

    public SelectedComponentCommands(DataCommands dataModel, Observables observables, SelectionCommands selectionCommands) {
        this.dataModel = dataModel;
        this.selectionCommands = selectionCommands;
        observables.selection().watch(new Observer<SelectionQuery>() {
            @Override public void update(SelectionQuery newValue) {
                selectionQuery = newValue;
            }
        });
    }

    public void replaceSelected(Component newComponent) {
        final Component selectedComponent = selectionQuery.selectedComponent();
        PlacementComponent parent = selectionQuery.parentOfSelected();
        dataModel.replace(parent, selectedComponent, newComponent);
        selectionCommands.selectComponent(parent, newComponent);
    }
}
