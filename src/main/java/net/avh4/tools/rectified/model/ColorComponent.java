package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public class ColorComponent implements Component {
    private final int color;

    public ColorComponent(int color) {
        this.color = color;
    }

    public int color() {
        return color;
    }

    @Override public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(bounds, color);
    }

    @Override public String toString() {
        return "ColorComponent{" +
                "color=0x" + Integer.toHexString(color) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorComponent that = (ColorComponent) o;

        if (color != that.color) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color;
    }
}
