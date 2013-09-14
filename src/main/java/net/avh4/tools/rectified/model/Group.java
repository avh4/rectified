package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import org.pcollections.PVector;

public abstract class Group implements Component {
    protected abstract Rect placedBoundsForChild(Rect rect, Component child);

    public abstract PVector<Component> children();

    public abstract Group swap(Component oldComponent, Component newComponent);

    @Override public void draw(Rect rect, GraphicsOperations g, FontMetricsService fm) {
        for (Component child : children()) {
            Rect placedBounds = placedBoundsForChild(rect, child);
            child.draw(placedBounds, g, fm);
        }
    }
}
