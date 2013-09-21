package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.Design;
import net.avh4.framework.uilayer.mvc.Observer;


public class DesignPanel implements Element {
    private Design design;

    public DesignPanel(final Observables observables) {
        observables.design().watch(new Observer() {
            public void update() {
                design = observables.design().get();
            }
        });
    }

    @Override public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        if (design == null) {
            g.drawText(fm, "(no vaild design)", bounds, Font.OPEN_SANS, Color.DARK_GRAY);
            return;
        }
        try {
            design.draw(bounds, g, fm);
        } catch (RuntimeException e) {
            g.drawText(fm, e.getLocalizedMessage(), bounds, Font.OPEN_SANS, Color.DARK_GRAY);
        }
    }
}
