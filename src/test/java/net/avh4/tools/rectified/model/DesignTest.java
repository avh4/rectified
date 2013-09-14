package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.TreePVector;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class DesignTest {
    private Design subject;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private Component c9;
    @Mock private Rect bounds;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new Design(TreePVector.singleton(c1).plus(c2));
    }

    @Test
    public void shouldDrawComponents() throws Exception {
        subject.draw(bounds, g, fm);
        verify(c1).draw(bounds, g, fm);
        verify(c2).draw(bounds, g, fm);
    }

    @Test
    public void swap_shouldReplaceComponent() throws Exception {
        assertThat(subject.swap(c1, c9).components()).containsExactly(c9, c2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void swap_withInvalidOldValue_shouldThrow() throws Exception {
        subject.swap(c9, c2);
    }

    @Test
    public void add_shouldAddComponent() throws Exception {
        assertThat(subject.add(c9).components()).containsExactly(c1, c2, c9);
    }
}
