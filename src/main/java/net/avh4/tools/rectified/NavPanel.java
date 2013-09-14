package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Group;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionCommands;

@Deprecated // collapse into Actions
public class NavPanel {
    private final SelectionCommands selectionCommands;

    public interface Actions {
        void select(Component... path);
    }

    public NavPanel(SelectionCommands selectionCommands) {
        this.selectionCommands = selectionCommands;
    }

    public NavPanel.Actions actions() {
        return new Actions() {
            @Override public void select(Component... path) {
                final int length = path.length;
                Group parent = null;
                if (length >= 2) {
                    parent = (Group) path[length - 2];
                }
                selectionCommands.selectComponent(parent, path[length - 1]);
            }
        };
    }
}

