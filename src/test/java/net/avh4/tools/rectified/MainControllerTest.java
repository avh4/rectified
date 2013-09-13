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
    @Mock private ErrorPanel errorPanel;
    @Mock private Design design;
    @Mock private DesignParser designParser;
    private String code = "___MOCK___CODE___";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new MainController(designPanel, designParser, errorPanel);
    }

    @Test
    public void codeDidChange_shouldSetPreview() throws Exception {
        stub(designParser.parse(code)).toReturn(design);
        subject.codeDidChange(code);
        verify(designPanel).setDesign(design);
    }

    @Test
    public void codeDidChange_shouldClearError() throws Exception {
        stub(designParser.parse(code)).toReturn(design);
        subject.codeDidChange(code);
        verify(errorPanel).clearError();
    }

    @Test
    public void codeDidChange_whenNewCodeDoesntParse_shouldNotSetPreview() throws Exception {
        stub(designParser.parse(code)).toThrow(new InvalidCodeException(""));
        subject.codeDidChange(code);
        verifyZeroInteractions(designPanel);
    }

    @Test
    public void codeDidChange_whenNewCodeDoesntParse_shouldDisplayError() throws Exception {
        final InvalidCodeException exception = new InvalidCodeException("Error message");
        stub(designParser.parse(code)).toThrow(exception);
        subject.codeDidChange(code);
        verify(errorPanel).setError(exception);
    }
}
