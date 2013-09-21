package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.PlacementComponent;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.placement.TopPlacement;
import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.framework.uilayer.mvc.Observer;
import org.pcollections.PStack;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

public class OverlayPanel implements Observer {
    private final DataCommands dataCommands;
    private final Channel<SelectionQuery> selection;
    private PStack<Component> path;

    public OverlayPanel(DataCommands dataCommands, Observables observables) {
        this.dataCommands = dataCommands;
        selection = observables.selection();
        observables.selection().watch(this);
    }

    @Override public void update() {
        path = selection.get().path();
    }

    public enum Edge {
        TOP, BOTTOM, LEFT, RIGHT
    }

    public interface Actions {
        void addPlacement(Edge edge, int size_dp);
    }

    public OverlayPanel.Actions actions() {
        return new Actions() {
            @Override public void addPlacement(Edge edge, int size_dp) {
                if (edge != Edge.TOP) throw new RuntimeException("Not impelmented: edge = " + edge);
                final TopPlacement placement = new TopPlacement(size_dp);
                final PVector<Component> components = TreePVector.<Component>singleton(new ColorComponent(0xffeeeeee));
                final PVector<Component> remainderComponents = TreePVector.empty();
                dataCommands.add(path, new PlacementComponent(placement, components, remainderComponents));
            }
        };
    }
}
