package net.avh4.tools.rectified;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CodePanelTest {
    private CodePanel subject;
    private String code = "___FAKE_CODE__";
    @Mock private CodePanel.Listener listener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new CodePanel(listener);
    }

    @Test
    public void replaceCode_shouldNotifyListener() throws Exception {
        subject.actions().replaceCode(code);
        verify(listener).codeDidChange(code);
    }
}
