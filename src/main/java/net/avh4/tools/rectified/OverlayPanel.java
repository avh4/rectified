package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import net.avh4.tools.rectified.model.placement.TopPlacement;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

public class OverlayPanel {
    private final DataCommands dataCommands;

    public OverlayPanel(DataCommands dataCommands) {
        this.dataCommands = dataCommands;
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
                final TopPlacement placement = new TopPlacement(size_dp);
                final PVector<Component> components = TreePVector.<Component>singleton(new ColorComponent(0xffeeeeee));
                final PVector<Component> remainderComponents = TreePVector.empty();
                dataCommands.add(new PlacementComponent(placement, components, remainderComponents));
            }
        };
    }
}
