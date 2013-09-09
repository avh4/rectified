package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Design;
import org.junit.Before;
import org.junit.Test;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;

public class DesignParserTest {

    private DesignParser subject;

    @Before
    public void setUp() throws Exception {
        subject = new DesignParser();
    }

    @Test
    public void shouldParseMainComponent() throws Exception {
        final Design design = subject.parse("" +
                "{\n" +
                "    \"design\": [\n" +
                "        {\n" +
                "            \"remainder\": true,\n" +
                "            \"background\": \"#DC143C\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        assertThat(design).isNotNull();
        assertThat(design.getMainComponent()).isNotNull();
    }

    @Test
    public void shouldParseBackgroundColor() throws Exception {
        final Design design = subject.parse("" +
                "{\n" +
                "    \"design\": [\n" +
                "        {\n" +
                "            \"remainder\": true,\n" +
                "            \"background\": \"#DC143C\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        assertThat(design.getMainComponent()).isEqualTo(new ColorComponent("#DC143C"));
    }

    @Test(expected = InvalidCodeException.class)
    public void withBadJson_shouldThrowInvalidCode() throws Exception {
        subject.parse("[}");
    }
}
