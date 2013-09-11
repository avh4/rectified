package net.avh4.tools.rectified.parser;

import net.avh4.framework.uilayer.Font;
import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.TextComponent;
import net.avh4.tools.rectified.model.placement.Placement;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class ComponentParserTest {

    private ComponentParser subject;
    @Mock private ColorParser colorParser;
    private int color1 = 0x456789;
    private int color2 = 0x56789a;
    @Mock private PlacementParser placementParser;
    @Mock private Placement p1;
    @Mock private FontParser fontParser;
    @Mock private Font font1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(colorParser.parse(anyString())).toReturn(color1).toReturn(color2).toThrow(new RuntimeException("Ran out of mock colors"));
        stub(placementParser.parse(anyString())).toReturn(p1).toThrow(new RuntimeException("Ran out of mock placements"));
        stub(fontParser.parse(anyString())).toReturn(font1).toThrow(new RuntimeException("Ran out of mock fonts"));
        subject = new ComponentParser(colorParser, placementParser, fontParser);
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

    @Test
    public void shouldParseTextComponent() throws Exception {
        assertThat(subject.parse("{\"text\":\"Welcome to the jungle\",\"color\":\"#123\",\"padding\":5}"))
                .isEqualTo(new TextComponent("Welcome to the jungle", font1, color1, 5));
        verify(fontParser).parse(null);
        verify(colorParser).parse("#123");
    }
}
