package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.placement.Placement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.TreePVector;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class PlacementComponentTest {
    private PlacementComponent subject;
    @Mock private Placement placement;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private Component c3;
    @Mock private Component c4;
    @Mock private Component c9;
    @Mock private Rect bounds;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;
    @Mock private Rect placedBounds;
    @Mock private Rect remainderBounds;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(placement.place(bounds)).toReturn(placedBounds);
        stub(placement.remainder(bounds)).toReturn(remainderBounds);
        final TreePVector<Component> components = TreePVector.singleton(c1).plus(c2);
        final TreePVector<Component> remainderComponents = TreePVector.singleton(c3).plus(c4);
        subject = new PlacementComponent(placement, components, remainderComponents);
    }

    @Test
    public void shouldDrawFirstComponentInPlacedBounds() throws Exception {
        subject.draw(bounds, g, fm);
        verify(c1).draw(placedBounds, g, fm);
    }

    @Test
    public void shouldDrawAllComponentInPlacedBounds() throws Exception {
        subject.draw(bounds, g, fm);
        verify(c1).draw(placedBounds, g, fm);
        verify(c2).draw(placedBounds, g, fm);
    }

    @Test
    public void shouldDrawRemainderComponentsInRemainderBounds() throws Exception {
        subject.draw(bounds, g, fm);
        verify(c3).draw(remainderBounds, g, fm);
        verify(c4).draw(remainderBounds, g, fm);
    }

    @Test
    public void swap_shouldUpdateComponent() throws Exception {
        assertThat(subject.swap(c1, c9).components()).containsExactly(c9, c2);
    }

    @Test
    public void swap_shouldUpdateRemainderComponent() throws Exception {
        assertThat(subject.swap(c4, c9).remainderComponents()).containsExactly(c3, c9);
    }
}
