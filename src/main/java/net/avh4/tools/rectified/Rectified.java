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
            @Override public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            @Override public Dimension getPreferredSize() {
                return new Dimension(320, 508);
            }
        };
        JPanel designWrapper = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(0xffffff);
                Color color2 = new Color(0xcccccc);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        designWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        designWrapper.add(designPanel);
        window.add(designWrapper, BorderLayout.WEST);
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
