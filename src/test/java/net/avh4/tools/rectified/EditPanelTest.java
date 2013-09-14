package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.uimodel.cqrs.SelectedComponentCommands;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class EditPanelTest {

    private EditPanel subject;
    @Mock private Component c1;
    @Mock private SelectedComponentCommands selectedComponentCommands;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new EditPanel(selectedComponentCommands);
    }

    @Test
    public void setColor_shouldUpdate() throws Exception {
        subject.actions().setColor(0x456456);
        verify(selectedComponentCommands).replaceSelected(new ColorComponent(0x456456));
    }
}
