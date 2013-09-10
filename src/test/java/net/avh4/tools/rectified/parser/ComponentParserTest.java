package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.model.ColorComponent;
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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(colorParser.parse(Mockito.anyString())).toReturn(color1).toThrow(new RuntimeException("Ran out of mock colors"));
        subject = new ComponentParser(colorParser);
    }

    @Test
    public void shouldParseColorComponent() throws Exception {
        assertThat(subject.parse("{\"color\":\"#890710\"}")).isEqualTo(new ColorComponent(color1));
        verify(colorParser).parse("#890710");
    }
}
