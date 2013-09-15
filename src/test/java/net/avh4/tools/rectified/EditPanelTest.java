package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.uimodel.cqrs.AppCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.ConstantObservable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.PStack;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class EditPanelTest {

    private EditPanel subject;
    @Mock private AppCommands appCommands;
    @Mock private Observables observables;
    @Mock private SelectionQuery selectionQuery;
    @Mock private PStack<Component> path;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(observables.selection()).toReturn(new ConstantObservable<>(selectionQuery));
        stub(selectionQuery.path()).toReturn(path);
        subject = new EditPanel(appCommands, observables);
    }

    @Test
    public void setColor_shouldUpdate() throws Exception {
        subject.actions().setColor(0x456456);
        verify(appCommands).replace(path, new ColorComponent(0x456456));
    }
}
