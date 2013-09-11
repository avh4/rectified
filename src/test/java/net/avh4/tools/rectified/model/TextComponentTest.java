package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TextComponentTest {

    private TextComponent subject;
    private String value = "Welcome to the jungle";
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;
    private Font font = Font.OPEN_SANS;
    private int color = Color.BLUE;
    private int padding = 5;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new TextComponent(value, font, color, padding);
    }

    @Test
    public void shouldDrawText() throws Exception {
        subject.draw(Rect.ofSize(200, 100), g, fm);
        verify(g).drawText(fm, value, Rect.ofSize(200, 100).inset(padding), font, color);
    }
}
