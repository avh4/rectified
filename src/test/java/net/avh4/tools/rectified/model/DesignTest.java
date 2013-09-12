package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.verify;

public class DesignTest {
    private Design subject;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private Rect bounds;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new Design(Arrays.asList(c1, c2));
    }

    @Test
    public void shouldDrawComponents() throws Exception {
        subject.draw(bounds, g, fm);
        verify(c1).draw(bounds, g, fm);
        verify(c2).draw(bounds, g, fm);
    }
}
