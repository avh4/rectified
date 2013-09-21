package net.avh4.tools.rectified.swing;

import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.Observables;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.framework.uilayer.mvc.Observer;

import javax.swing.*;
import java.awt.*;

public class OverlayPanelView extends JPanel implements Observer {
    private final Channel<SelectionQuery> selection;
    Rect selectionBounds = null;

    public OverlayPanelView(Observables observables) {
        setOpaque(false);

        selection = observables.selection();
    }

    @Override public void update() {
        selectionBounds = selection.get().selectionBounds();
        repaint();
    }

    @Override protected void paintComponent(Graphics g) {
        if (selectionBounds != null) {
            g.setColor(Color.RED);
            g.drawRect((int) selectionBounds.minX(), (int) selectionBounds.minY(), (int) selectionBounds.width() - 1, (int) selectionBounds.height() - 1);
        }
    }
}
