package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class DesignPanelTest {

    private DesignPanel subject;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;

    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private Design design;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(design.components()).toReturn(new Component[]{c1, c2});
        subject = new DesignPanel();
        subject.setDesign(design);
    }

    @Test
    public void shouldRenderMainComponent() throws Exception {
        subject.draw(Rect.ofSize(320, 508), g, fm);
        verify(c1).draw(Rect.ofSize(320, 508), g, fm);
        verify(c2).draw(Rect.ofSize(320, 508), g, fm);
    }
}
