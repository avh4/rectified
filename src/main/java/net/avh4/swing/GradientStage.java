package net.avh4.swing;

import javax.swing.*;
import java.awt.*;

public class GradientStage extends JPanel {
    public static final int PADDING = 20;
    public static final Color TOP_LEFT_COLOR = new Color(0xffffff);
    public static final Color BOTTOM_RIGHT_COLOR = new Color(0xcccccc);

    public GradientStage(JComponent child) {
        super(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.add(child);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = TOP_LEFT_COLOR;
        Color color2 = BOTTOM_RIGHT_COLOR;
        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
