package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public class Design {
    private final Group mainComponent;

    public Design(Group mainComponent) {
        this.mainComponent = mainComponent;
    }

    public Group mainComponent() {
        return mainComponent;
    }

    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        mainComponent.draw(bounds, g, fm);
    }

    public Design swap(final Group oldComponent, final Group newComponent) {
        if (!oldComponent.equals(mainComponent))
            throw new IllegalArgumentException("Tried to swap a component that doesn't exist in the design: " + "\n  Current: " + mainComponent + "\n  Component to remove: " + oldComponent + "\n  New component: " + newComponent);
        return new Design(newComponent);
    }

    @Override public String toString() {
        return "Design{" +
                "mainComponent=" + mainComponent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Design design = (Design) o;

        if (mainComponent != null ? !mainComponent.equals(design.mainComponent) : design.mainComponent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mainComponent != null ? mainComponent.hashCode() : 0;
    }
}
