package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ColorComponentTest {
    private ColorComponent subject;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new ColorComponent("#F5DEB3");
    }

    @Test
    public void shouldDrawColor() throws Exception {
        final Rect bounds = Rect.ofSize(50, 60);
        subject.draw(bounds, g, fm);
        verify(g).drawRect(bounds, 0xFFF5DEB3);
    }
}
