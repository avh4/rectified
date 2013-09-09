package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public class DesignPanel implements Element {
    @Override public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(bounds, 0xffDC143C);
    }
}
