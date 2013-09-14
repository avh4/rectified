package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.PlacementComponent;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.placement.TopPlacement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.TreePVector;

import static org.mockito.Mockito.verify;

public class OverlayPanelTest {

    private OverlayPanel subject;
    @Mock private DataCommands dataCommands;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new OverlayPanel(dataCommands);
    }

    @Test
    public void addPlacement_shouldAddNewPlacementComponent() throws Exception {
        subject.actions().addPlacement(OverlayPanel.Edge.TOP, 48);
        verify(dataCommands).add(new PlacementComponent(new TopPlacement(48),
                TreePVector.<Component>singleton(new ColorComponent(0xffeeeeee)), TreePVector.<Component>empty()));
    }
}
