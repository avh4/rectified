package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class DesignTest {
    private Design subject;
    @Mock private Rect bounds;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;
    @Mock private Group p1;
    @Mock private Group p9;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new Design(p1);
    }

    @Test
    public void shouldDrawComponents() throws Exception {
        subject.draw(bounds, g, fm);
        verify(p1).draw(bounds, g, fm);
    }

    @Test
    public void swap_shouldReplaceComponent() throws Exception {
        assertThat(subject.swap(p1, p9).mainComponent()).isSameAs(p9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void swap_withInvalidOldValue_shouldThrow() throws Exception {
        subject.swap(p9, p1);
    }
}
