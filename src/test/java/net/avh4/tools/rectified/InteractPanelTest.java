package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.uimodel.cqrs.AppCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.ConstantObservable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class InteractPanelTest {

    private InteractPanel subject;
    private Rect selection;
    private Rect bounds;
    @Mock private GraphicsOperations g;
    @Mock private FontMetricsService fm;
    @Mock private Observables observables;
    @Mock private SelectionQuery selectionQuery;
    @Mock private AppCommands appCommands;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bounds = Rect.ofSize(100, 100);
        selection = bounds.top(20);
        stub(observables.selection()).toReturn(new ConstantObservable<>(selectionQuery));
        stub(selectionQuery.selectionBounds()).toReturn(selection);
        subject = new InteractPanel(observables, appCommands);
    }

    @Test
    public void moveMouse_inCenterOfSelection_shouldHighlightEntireArea() throws Exception {
        subject.move(bounds, selection.center());
        subject.draw(bounds, g, fm);
        verify(g).drawRect(selection.inset(3), Color.fromWA(0.3, 0.3));
    }

    @Test
    public void moveMouse_inLeftOfSelection_shouldHighlightLeft() throws Exception {
        subject.move(bounds, selection.leftPercent(0.2).center());
        subject.draw(bounds, g, fm);
        verify(g).drawRect(selection.leftPercent(0.3).inset(3), Color.fromWA(0.3, 0.3));
    }
}
