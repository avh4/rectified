package net.avh4.tools.rectified.model.placement;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.Component;

public class PlacementComponent implements Component {
    private final Placement placement;
    private final Iterable<Component> components;
    private final Iterable<Component> remainderComponents;

    public PlacementComponent(Placement placement, Iterable<Component> components, Iterable<Component> remainderComponents) {
        this.placement = placement;
        this.components = components;
        this.remainderComponents = remainderComponents;
    }

    @Override public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        Rect placedBounds = placement.place(bounds);
        for (Component component : components) {
            component.draw(placedBounds, g, fm);
        }
        Rect remainderBounds = placement.remainder(bounds);
        for (Component component : remainderComponents) {
            component.draw(remainderBounds, g, fm);
        }
    }

    @Override public String toString() {
        return "PlacementComponent{" +
                "placement=" + placement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlacementComponent that = (PlacementComponent) o;

        if (placement != null ? !placement.equals(that.placement) : that.placement != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return placement != null ? placement.hashCode() : 0;
    }
}
