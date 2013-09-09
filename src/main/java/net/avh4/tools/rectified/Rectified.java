package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import javax.swing.*;
import java.awt.*;

public class Rectified {

    public static void main(String[] args) {
        MutablePicoContainer pico = new DefaultPicoContainer();
        ApplicationModule.configure(pico);
        final RectifiedApp app = pico.getComponent(RectifiedApp.class);
        JFrame window = new JFrame("Rectified α");
        window.setLayout(new BorderLayout());
        JComponent designPanel = new SwingSceneRenderer(app.designPanel()) {
            @Override public Dimension getPreferredSize() {
                return new Dimension(320, 508);
            }
        };
        window.add(designPanel, BorderLayout.WEST);
        final JTextArea codePanel = new JTextArea("{\"design\": []}") {
            @Override public Dimension getPreferredSize() {
                return new Dimension(400, 400);
            }
        };
        codePanel.setMargin(new Insets(20, 20, 20, 20));
        window.add(codePanel, BorderLayout.CENTER);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
