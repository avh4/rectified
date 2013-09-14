package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import net.avh4.tools.rectified.uimodel.cqrs.SelectedComponentCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.ConstantObservable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class SelectedComponentCommandsTest {

    private SelectedComponentCommands subject;
    @Mock private DataCommands dataCommands;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private SelectionQuery selectionQuery;
    @Mock private PlacementComponent parent;
    @Mock private Observables observables;
    @Mock private SelectionCommands selectionCommands;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(observables.selection()).toReturn(new ConstantObservable<>(selectionQuery));
        subject = new SelectedComponentCommands(dataCommands, observables, selectionCommands);
    }

    @Test
    public void replaceSelected_shouldReplaceSelectedComponent() throws Exception {
        stub(selectionQuery.selectedComponent()).toReturn(c1);
        stub(selectionQuery.parentOfSelected()).toReturn(parent);
        subject.replaceSelected(c2);
        verify(dataCommands).replace(parent, c1, c2);
    }

    @Test
    public void replaceSelected_shouldUpdateSelection() throws Exception {
        stub(selectionQuery.selectedComponent()).toReturn(c1);
        stub(selectionQuery.parentOfSelected()).toReturn(parent);
        subject.replaceSelected(c2);
        verify(selectionCommands).selectComponent(parent, c2);
    }
}
