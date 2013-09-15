package net.avh4.tools.rectified.swing;

import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.Observables;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Group;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.Observer;

import javax.swing.*;
import java.awt.*;

public class OverlayPanelView extends JPanel {
    Rect selectionBounds = null;

    public OverlayPanelView(Observables observables) {
        setOpaque(false);

        observables.selection().watch(new Observer<SelectionQuery>() {
            @Override public void update(SelectionQuery newValue) {
                Rect selectedBounds = Rect.ofSize(320, 508);
                Component[] components = new Component[newValue.path().size()];
                int i = components.length;
                for (Component component : newValue.path()) {
                    components[--i] = component;
                }
                for (int j = 0; j < components.length - 1; j++) {
                    Group parent = (Group) components[j];
                    selectedBounds = parent.placedBoundsForChild(selectedBounds, components[j + 1]);
                }
                selectionBounds = selectedBounds;
                repaint();
            }
        });
    }

    @Override protected void paintComponent(Graphics g) {
        if (selectionBounds != null) {
            g.setColor(Color.RED);
            g.drawRect((int) selectionBounds.minX(), (int) selectionBounds.minY(), (int) selectionBounds.width() - 1, (int) selectionBounds.height() - 1);
        }
    }
}
