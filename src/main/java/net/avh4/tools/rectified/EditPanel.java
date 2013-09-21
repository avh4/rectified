package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.tools.rectified.uimodel.cqrs.AppCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.framework.uilayer.mvc.Observer;

@Deprecated // collapse with Actions
public class EditPanel implements Observer {
    private final Channel<SelectionQuery> selection;
    private AppCommands dataCommands;
    private SelectionQuery selectionQuery;

    public interface Actions {
        void setColor(int color);
    }

    public EditPanel(AppCommands dataCommands, Observables observables) {
        this.dataCommands = dataCommands;
        selection = observables.selection();
        observables.selection().watch(this);
    }

    @Override public void update() {
        selectionQuery = selection.get();
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
