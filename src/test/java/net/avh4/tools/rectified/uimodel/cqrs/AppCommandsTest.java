package net.avh4.tools.rectified.uimodel.cqrs;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.PStack;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class AppCommandsTest {

    private AppCommands subject;
    @Mock private DataCommands dataCommands;
    @Mock private SelectionCommands selectionCommands;
    @Mock private PStack<Component> path;
    @Mock private PStack<Component> pathʹ;
    @Mock private Component c1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(dataCommands.replace(path, c1)).toReturn(pathʹ);
        subject = new AppCommands(dataCommands, selectionCommands);
    }

    @Test
    public void replace_shouldUpdateData() throws Exception {
        subject.replace(path, c1);
        verify(dataCommands).replace(path, c1);
    }

    @Test
    public void replace_shouldUpdateSelection() throws Exception {
        subject.replace(path, c1);
        verify(selectionCommands).selectComponent(pathʹ);
    }
}
