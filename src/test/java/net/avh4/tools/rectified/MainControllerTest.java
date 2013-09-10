package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.parser.DesignParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MainControllerTest {
    private MainController subject;
    @Mock private DesignPanel designPanel;
    @Mock private Design design;
    @Mock private DesignParser designParser;
    private String code = "___MOCK___CODE___";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new MainController(designPanel, designParser);
    }

    @Test
    public void codeDidChange_shouldSetPreview() throws Exception {
        stub(designParser.parse(code)).toReturn(design);
        subject.codeDidChange(code);
        verify(designPanel).setDesign(design);
    }

    @Test
    public void codeDidChange_whenNewCodeDoesntParse_shouldNotSetPreview() throws Exception {
        stub(designParser.parse(code)).toThrow(new InvalidCodeException(""));
        subject.codeDidChange(code);
        verifyZeroInteractions(designPanel);
    }
}
