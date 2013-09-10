package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class DesignParserTest {

    private DesignParser subject;
    @Mock private ComponentParser componentParser;
    @Mock private Component c1;
    @Mock private Component c2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(componentParser.parse(Mockito.anyString())).toReturn(c1).toReturn(c2).toThrow(new RuntimeException("Ran out of mock components"));
        subject = new DesignParser(componentParser);
    }

    @Test
    public void shouldParseMainComponent() throws Exception {
        final Design design = subject.parse("" +
                "{\n" +
                "    \"design\": [\n" +
                "        {\n" +
                "            \"color\": \"#DC143C\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        assertThat(design).isNotNull();
        assertThat(design.components()[0]).isNotNull();
    }

    @Test
    public void shouldParseFirstComponent() throws Exception {
        final Design design = subject.parse("" +
                "{\n" +
                "    \"design\": [\n" +
                "        {\"color\": \"#DC143C\"}\n" +
                "    ]\n" +
                "}");
        verify(componentParser).parse("{\"color\":\"#DC143C\"}");
        assertThat(design.components()[0]).isSameAs(c1);
    }

    @Test
    public void shouldParseAllComponents() throws Exception {
        final Design design = subject.parse("" +
                "{\n" +
                "    \"design\": [\n" +
                "        {\"color\": \"#DC143C\"},\n" +
                "        {\"color\": \"#AB143A\"}\n" +
                "    ]\n" +
                "}");
        verify(componentParser).parse("{\"color\":\"#DC143C\"}");
        verify(componentParser).parse("{\"color\":\"#AB143A\"}");
        assertThat(design.components()).isEqualTo(new Component[]{c1, c2});
    }

    @Test(expected = InvalidCodeException.class)
    public void withBadJson_shouldThrowInvalidCode() throws Exception {
        subject.parse("[}");
    }
}
