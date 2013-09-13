package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.placement.Placement;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class PlacementComponentTest {
    private PlacementComponent subject;
    @Mock private Placement placement;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private Component c3;
    @Mock private Component c4;
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
        subject = new PlacementComponent(placement, Arrays.asList(c1, c2), Arrays.asList(c3, c4));
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
}
