package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import net.avh4.util.lisp.LispParser;
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
    @Mock private RectifiedLispParser lispParser;
    @Mock private Design design;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(lispParser.parse("(design)")).toReturn(design);
        subject = new DesignParser(lispParser);
    }

    @Test
    public void shouldParseDesign() throws Exception {
        final Design design = subject.parse("(design)");
        assertThat(design).isSameAs(design);
    }

    @Test(expected = InvalidCodeException.class)
    public void withBadLisp_shouldThrowInvalidCode() throws Exception {
        subject.parse(")");
    }
}
