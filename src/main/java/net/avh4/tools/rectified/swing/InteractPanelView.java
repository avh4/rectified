package net.avh4.tools.rectified.swing;

import net.avh4.framework.uilayer.swing.SwingInputHandler;
import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import net.avh4.tools.rectified.InteractPanel;

public class InteractPanelView extends SwingSceneRenderer {
    public InteractPanelView(InteractPanel panel) {
        super(panel);
        setOpaque(false);
        final SwingInputHandler handler = new SwingInputHandler(panel, panel, this);
        this.addMouseMotionListener(handler);
        this.addMouseListener(handler);
    }
}
