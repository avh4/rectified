package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public interface Component extends Element {
    void draw(Rect rect, GraphicsOperations g, FontMetricsService fm);

    String navString();
}
