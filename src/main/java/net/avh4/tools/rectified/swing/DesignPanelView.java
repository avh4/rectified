package net.avh4.tools.rectified.swing;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.swing.SwingSceneRenderer;

import java.awt.*;

public class DesignPanelView extends SwingSceneRenderer {
    public DesignPanelView(Element designPanel) {
        super(designPanel);
    }

    @Override public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(320, 508);
    }
}
