package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.Observer;

@Deprecated // collapse with Actions
public class EditPanel {
    private DataCommands dataCommands;
    private SelectionQuery selectionQuery;

    public interface Actions {
        void setColor(int color);
    }

    public EditPanel(DataCommands dataCommands, Observables observables) {
        this.dataCommands = dataCommands;
        observables.selection().watch(new Observer<SelectionQuery>() {
            @Override public void update(SelectionQuery newValue) {
                selectionQuery = newValue;
            }
        });
    }

    public EditPanel.Actions actions() {
        return new Actions() {
            @Override public void setColor(int color) {
                final ColorComponent newComponent = new ColorComponent(color);
                dataCommands.replace(selectionQuery.path(), newComponent);
            }
        };
    }
}
