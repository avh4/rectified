package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.mvc.ConstantChannel;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.Design;
import net.avh4.util.test.FakeChannel;
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

    @Mock private Design design;
    @Mock private Observables observables;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(observables.design()).toReturn(new ConstantChannel<>(design));
        subject = new DesignPanel(observables);
    }

    @Test
    public void shouldRenderDesignComponent() throws Exception {
        subject.draw(Rect.ofSize(320, 508), g, fm);
        verify(design).draw(Rect.ofSize(320, 508), g, fm);
    }
}
