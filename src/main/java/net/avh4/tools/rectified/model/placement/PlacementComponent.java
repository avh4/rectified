package net.avh4.tools.rectified.model.placement;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.Component;
import org.pcollections.PVector;

public class PlacementComponent implements Component {
    private final Placement placement;
    private final PVector<Component> components;
    private final PVector<Component> remainderComponents;

    public PlacementComponent(Placement placement, PVector<Component> components, PVector<Component> remainderComponents) {
        this.placement = placement;
        this.components = components;
        this.remainderComponents = remainderComponents;
    }

    public PVector<Component> components() {
        return components;
    }

    public PVector<Component> remainderComponents() {
        return remainderComponents;
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

    public PlacementComponent swap(Component oldComponent, Component newComponent) {
        if (components.contains(oldComponent)) {
            int index = components.indexOf(oldComponent);
            final PVector<Component> newComponents = components.with(index, newComponent);
            return new PlacementComponent(placement, newComponents, remainderComponents);
        } else {
            int index = remainderComponents.indexOf(oldComponent);
            PVector<Component> newComponents = remainderComponents.with(index, newComponent);
            return new PlacementComponent(placement, components, newComponents);
        }
    }

    @Override public String toString() {
        return "PlacementComponent{" +
                "placement=" + placement +
                ", components=" + components +
                ", remainderComponents=" + remainderComponents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlacementComponent that = (PlacementComponent) o;

        if (components != null ? !components.equals(that.components) : that.components != null) return false;
        if (placement != null ? !placement.equals(that.placement) : that.placement != null) return false;
        if (remainderComponents != null ? !remainderComponents.equals(that.remainderComponents) : that.remainderComponents != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = placement != null ? placement.hashCode() : 0;
        result = 31 * result + (components != null ? components.hashCode() : 0);
        result = 31 * result + (remainderComponents != null ? remainderComponents.hashCode() : 0);
        return result;
    }
}
