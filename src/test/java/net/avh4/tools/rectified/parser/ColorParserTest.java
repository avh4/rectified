package net.avh4.tools.rectified.parser;

import org.junit.Before;
import org.junit.Test;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;

public class ColorParserTest {
    private ColorParser subject;

    @Before
    public void setUp() throws Exception {
        subject = new ColorParser();
    }

    @Test
    public void shouldParseSixDigitHex() throws Exception {
        assertThat(subject.parse("#8903fa")).isEqualTo(0xff8903fa);
    }

    @Test
    public void shouldParseThreeDigitHex() throws Exception {
        assertThat(subject.parse("#468")).isEqualTo(0xff446688);
    }
}
