package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public class PlacementComponent implements Component {
    private final Placement placement;
    private final Component[] components;

    public PlacementComponent(Placement placement, Component[] components) {
        this.placement = placement;
        this.components = components;
    }

    @Override public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        Rect placedBounds = placement.place(bounds);
        components[0].draw(placedBounds, g, fm);
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