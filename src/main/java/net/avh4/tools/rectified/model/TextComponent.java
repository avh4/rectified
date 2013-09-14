package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public class TextComponent implements Component {
    private final String value;
    private final Font font;
    private final int color;
    private final int padding;

    public TextComponent(String value, Font font, int color, int padding) {
        this.value = value;
        this.font = font;
        this.color = color;
        this.padding = padding;
    }

    @Override public void draw(Rect rect, GraphicsOperations g, FontMetricsService fm) {
        g.drawText(fm, value, rect.inset(padding), font, color);
    }

    @Override public String navString() {
        return "Text: “" + value + "”";
    }

    @Override public String toString() {
        return "TextComponent{" +
                "value='" + value + '\'' +
                ", font=" + font +
                ", color=" + color +
                ", padding=" + padding +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComponent that = (TextComponent) o;

        if (color != that.color) return false;
        if (padding != that.padding) return false;
        if (font != null ? !font.equals(that.font) : that.font != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (font != null ? font.hashCode() : 0);
        result = 31 * result + color;
        result = 31 * result + padding;
        return result;
    }
}
