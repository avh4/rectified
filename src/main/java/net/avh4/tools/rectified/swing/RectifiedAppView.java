package net.avh4.tools.rectified.swing;

import net.avh4.swing.GradientStage;
import net.avh4.tools.rectified.AppConfig;

import javax.swing.*;
import java.awt.*;

public class RectifiedAppView extends JFrame {
    public RectifiedAppView(AppConfig config, DesignPanelView designPanel, NavPanelView navPanel, OverlayPanelView overlayPanel, EditPanelView editPanel) throws HeadlessException {
        super(config.appName());
        this.setLayout(new BorderLayout());
        this.add(navPanel, BorderLayout.WEST);
        this.add(new GradientStage(designPanel, overlayPanel), BorderLayout.CENTER);
        this.add(editPanel, BorderLayout.EAST);
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
