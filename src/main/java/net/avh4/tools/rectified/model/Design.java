package net.avh4.tools.rectified.model;

import com.google.common.collect.Iterables;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import org.pcollections.PVector;

public class Design {
    private final PVector<Component> components;

    public Design(PVector<Component> components) {
        this.components = components;
    }

    public PVector<Component> components() {
        return components;
    }

    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        for (Component component : components) {
            component.draw(bounds, g, fm);
        }
    }

    public Design swap(final Component oldComponent, final Component newComponent) {
        int index = components.indexOf(oldComponent);
        if (index == -1)
            throw new IllegalArgumentException("Tried to swap a component that doesn't exist in the design: " + oldComponent);
        final PVector<Component> newComponents = components.with(index, newComponent);
        return new Design(newComponents);
    }

    public Design add(Component component) {
        final PVector<Component> newComponents = components.plus(component);
        return new Design(newComponents);
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
