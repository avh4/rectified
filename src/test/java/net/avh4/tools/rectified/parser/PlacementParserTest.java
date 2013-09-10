package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.model.BottomPlacement;
import net.avh4.tools.rectified.model.TopPlacement;
import org.junit.Before;
import org.junit.Test;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;

public class PlacementParserTest {
    private PlacementParser subject;

    @Before
    public void setUp() throws Exception {
        subject = new PlacementParser();
    }

    @Test
    public void shouldParseTopPlacement() throws Exception {
        assertThat(subject.parse("{\"top\":48}")).isEqualTo(new TopPlacement(48));
    }

    @Test
    public void shouldParseBottomPlacement() throws Exception {
        assertThat(subject.parse("{\"bottom\":44}")).isEqualTo(new BottomPlacement(44));
    }
}
