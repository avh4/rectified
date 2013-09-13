package net.avh4.tools.rectified.swing;

import net.avh4.swing.GradientStage;
import net.avh4.tools.rectified.AppConfig;

import javax.swing.*;
import java.awt.*;

public class RectifiedAppView extends JFrame {
    public RectifiedAppView(AppConfig config, DesignPanelView designPanel, CodePanelView codePanel, ErrorPanelView errorPanel) throws HeadlessException {
        super(config.appName());
        this.setLayout(new BorderLayout());
        this.add(new GradientStage(designPanel), BorderLayout.WEST);
        codePanel.setText(config.defaultDesign());
        this.add(codePanel, BorderLayout.CENTER);
        this.add(errorPanel, BorderLayout.SOUTH);
    }

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
