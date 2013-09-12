package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

import java.util.Arrays;

public class Design {
    private final Component[] components;

    public Design(Component[] components) {
        this.components = components;
    }

    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        for (Component component : components) {
            component.draw(bounds, g, fm);
        }
    }

    @Override public String toString() {
        return "Design{" +
                "components=" + Arrays.toString(components) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Design design = (Design) o;

        if (!Arrays.equals(components, design.components)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return components != null ? Arrays.hashCode(components) : 0;
    }
}
