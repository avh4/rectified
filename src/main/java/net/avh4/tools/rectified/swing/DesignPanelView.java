package net.avh4.tools.rectified.swing;

import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import net.avh4.tools.rectified.DesignPanel;
import net.avh4.tools.rectified.Observables;
import net.avh4.tools.rectified.model.Design;
import net.avh4.util.Observer;

import java.awt.*;

public class DesignPanelView extends SwingSceneRenderer {
    public DesignPanelView(DesignPanel designPanel, Observables observables) {
        super(designPanel);
        observables.design().watch(new Observer<Design>() {
            @Override public void update(Design newValue) {
                repaint();
            }
        });
    }

    @Override public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(320, 508);
    }
}
