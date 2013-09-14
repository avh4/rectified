package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionCommands;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.TreePVector;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class NavPanelTest {

    private NavPanel subject;
    @Mock private Design design;
    @Mock private Component c1;
    @Mock private Component c2;
    @Mock private SelectionCommands selectionCommands;
    @Mock private PlacementComponent p1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(design.components()).toReturn(TreePVector.singleton(c1));
        subject = new NavPanel(selectionCommands);
    }

    @Test
    public void select_childOfRoot_shouldUpdateSelection() throws Exception {
        subject.actions().select(c1);
        verify(selectionCommands).selectComponent(null, c1);
    }

    @Test
    public void select_childOfChild_shouldUpdateSelectionAndParent() throws Exception {
        stub(design.components()).toReturn(TreePVector.singleton(c1).plus(p1));
        stub(p1.components()).toReturn(TreePVector.singleton(c2));
        subject.actions().select(p1, c2);
        verify(selectionCommands).selectComponent(p1, c2);
    }
}
