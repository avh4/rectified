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

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class PlacementComponentTest {
    private PlacementComponent subject;
    @Mock private Placement placement;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private Rect bounds;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;
    @Mock private Rect placedBounds;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(placement.place(bounds)).toReturn(placedBounds);
        subject = new PlacementComponent(placement, new Component[]{c1, c2});
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
}
