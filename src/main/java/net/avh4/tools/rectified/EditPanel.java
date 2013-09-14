package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.uimodel.cqrs.SelectedComponentCommands;

@Deprecated // collapse with Actions
public class EditPanel {
    private SelectedComponentCommands selectedComponentCommands;

    public EditPanel(SelectedComponentCommands selectedComponentCommands) {
        this.selectedComponentCommands = selectedComponentCommands;
    }

    public interface Actions {
        void setColor(int color);
    }

    public EditPanel.Actions actions() {
        return new Actions() {
            @Override public void setColor(int color) {
                final ColorComponent newComponent = new ColorComponent(color);
                selectedComponentCommands.replaceSelected(newComponent);
            }
        };
    }
}
