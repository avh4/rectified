package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Rectified {

    public static void main(String[] args) {
        MutablePicoContainer pico = new DefaultPicoContainer();
        ApplicationModule.configure(pico);
        final RectifiedApp app = pico.getComponent(RectifiedApp.class);

        JFrame window = new JFrame("Rectified α");
        window.setLayout(new BorderLayout());
        final JComponent designPanel = new SwingSceneRenderer(app.designPanel()) {
            @Override public Dimension getPreferredSize() {
                return new Dimension(320, 508);
            }
        };
        window.add(designPanel, BorderLayout.WEST);
        final JTextArea codePanel = new JTextArea() {
            @Override public Dimension getPreferredSize() {
                return new Dimension(400, 400);
            }
        };
        codePanel.setMargin(new Insets(20, 20, 20, 20));
        codePanel.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                app.codePanel().actions().replaceCode(codePanel.getText());
                designPanel.repaint();
            }

            @Override public void removeUpdate(DocumentEvent e) {
                app.codePanel().actions().replaceCode(codePanel.getText());
                designPanel.repaint();
            }

            @Override public void changedUpdate(DocumentEvent e) {
                app.codePanel().actions().replaceCode(codePanel.getText());
                designPanel.repaint();
            }
        });
        codePanel.setText("{\"design\": [{\"background\":\"#eeeeee\"}]}");
        window.add(codePanel, BorderLayout.CENTER);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
