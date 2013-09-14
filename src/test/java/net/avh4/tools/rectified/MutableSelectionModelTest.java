package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.uimodel.MutableSelectionModel;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.Observer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.PStack;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.stub;

public class MutableSelectionModelTest {

    private MutableSelectionModel subject;
    @Mock private Component c1;
    @Mock private PStack<Component> path;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(path.get(0)).toReturn(c1);
        subject = new MutableSelectionModel();
    }

    @Test
    public void selectComponent_shouldChangeSelectedComponent() throws Exception {
        subject.selectComponent(path);
        subject.watch(new Observer<SelectionQuery>() {
            @Override public void update(SelectionQuery newValue) {
                assertThat(newValue.selectedComponent()).isSameAs(c1);
            }
        });
    }

    @Test
    public void selectComponent_shouldChangeParent() throws Exception {
        subject.selectComponent(path);
        subject.watch(new Observer<SelectionQuery>() {
            @Override public void update(SelectionQuery newValue) {
                assertThat(newValue.path()).isSameAs(path);
            }
        });
    }
}
