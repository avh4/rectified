package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.placement.Placement;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class ComponentParserTest {

    private ComponentParser subject;
    @Mock private ColorParser colorParser;
    private int color1 = 0x456789;
    private int color2 = 0x56789a;
    @Mock private Placement p1;
    @Mock private PlacementParser placementParser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(colorParser.parse(Mockito.anyString())).toReturn(color1).toReturn(color2).toThrow(new RuntimeException("Ran out of mock colors"));
        stub(placementParser.parse(Mockito.anyString())).toReturn(p1).toThrow(new RuntimeException("Ran out of mock placements"));
        subject = new ComponentParser(colorParser, placementParser);
    }

    @Test
    public void shouldParseColorComponent() throws Exception {
        assertThat(subject.parse("{\"color\":\"#890710\"}")).isEqualTo(new ColorComponent(color1));
        verify(colorParser).parse("#890710");
    }

    @Test
    public void shouldParsePlacementComponentSubcomponents() throws Exception {
        assertThat(subject.parse("{\"placement\":{\"top\":48},\"inside\":[{\"color\":\"#123456\"}, {\"color\":\"#234567\"}]}"))
                .isEqualTo(new PlacementComponent(p1, new Component[]{new ColorComponent(color1), new ColorComponent(color2)}));
        verify(placementParser).parse("{\"top\":48}");
        verify(colorParser).parse("#123456");
        verify(colorParser).parse("#234567");
    }
}
