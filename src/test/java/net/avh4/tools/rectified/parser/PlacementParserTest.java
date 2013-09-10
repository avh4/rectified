package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.model.placement.BottomPlacement;
import net.avh4.tools.rectified.model.placement.LeftPlacement;
import net.avh4.tools.rectified.model.placement.RightPlacement;
import net.avh4.tools.rectified.model.placement.TopPlacement;
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

    @Test
    public void shouldParseLeftPlacement() throws Exception {
        assertThat(subject.parse("{\"left\":44}")).isEqualTo(new LeftPlacement(44));
    }

    @Test
    public void shouldParseRightPlacement() throws Exception {
        assertThat(subject.parse("{\"right\":44}")).isEqualTo(new RightPlacement(44));
    }
}
