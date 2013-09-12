package net.avh4.tools.rectified.model;

import com.google.common.collect.Iterables;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public class Design {
    private final Iterable<Component> components;

    public Design(Iterable<Component> components) {
        this.components = components;
    }

    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        for (Component component : components) {
            component.draw(bounds, g, fm);
        }
    }

    @Override public String toString() {
        return "Design{" +
                "components=" + Iterables.toString(components) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Design design = (Design) o;

        if (components != null ? !components.equals(design.components) : design.components != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }
}
