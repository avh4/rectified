package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionCommands;
import org.pcollections.PStack;

@Deprecated // collapse into Actions
public class NavPanel {
    private final SelectionCommands selectionCommands;

    public interface Actions {
        void select(PStack<Component> path);
    }

    public NavPanel(SelectionCommands selectionCommands) {
        this.selectionCommands = selectionCommands;
    }

    public NavPanel.Actions actions() {
        return new Actions() {
            @Override public void select(PStack<Component> path) {
                selectionCommands.selectComponent(path);
            }
        };
    }
}

