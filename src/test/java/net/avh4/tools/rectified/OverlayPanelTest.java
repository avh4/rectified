package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.PlacementComponent;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.placement.TopPlacement;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.ConstantObservable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.PStack;
import org.pcollections.TreePVector;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class OverlayPanelTest {

    private OverlayPanel subject;
    @Mock private DataCommands dataCommands;
    @Mock private PStack<Component> path;
    @Mock private Observables observables;
    @Mock private SelectionQuery selectionQuery;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(observables.selection()).toReturn(new ConstantObservable<>(selectionQuery));
        stub(selectionQuery.path()).toReturn(path);
        subject = new OverlayPanel(dataCommands, observables);
    }

    @Test
    public void addPlacement_shouldAddNewPlacementComponent() throws Exception {
        subject.actions().addPlacement(OverlayPanel.Edge.TOP, 48);
        final TreePVector<Component> expectedComponents = TreePVector.<Component>singleton(new ColorComponent(0xffeeeeee));
        final TreePVector<Component> expectedRemainderComponents = TreePVector.empty();
        verify(dataCommands).add(path,
                new PlacementComponent(new TopPlacement(48), expectedComponents, expectedRemainderComponents));
    }
}
