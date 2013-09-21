package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.uimodel.MutableSelectionModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.stub;

public class MutableSelectionModelTest {

    private MutableSelectionModel subject;
    @Mock private Component c1;
    private PStack<Component> path;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        path = ConsPStack.singleton(c1);
        subject = new MutableSelectionModel();
    }

    @Test
    public void selectComponent_shouldChangeSelectedComponent() throws Exception {
        subject.selectComponent(path);
        assertThat(subject.get().selectedComponent()).isSameAs(c1);
    }

    @Test
    public void selectComponent_shouldChangeParent() throws Exception {
        subject.selectComponent(path);
        assertThat(subject.get().path()).isSameAs(path);
    }
}
