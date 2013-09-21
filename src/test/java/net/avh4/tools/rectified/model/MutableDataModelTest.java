package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.mvc.Observer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class MutableDataModelTest {

    private MutableDataModel subject;
    @Mock private Design design2;
    @Mock private Observer observer;
    @Mock private Component c1;
    @Mock private Component c1ʹ;
    @Mock private Component c2;
    @Mock private Group rootp1c1;
    @Mock private Group rootʹc1;
    @Mock private Group rootʹp1ʹc1ʹ;
    @Mock private Group rootʹp1ʹc1c2;
    @Mock private Group p1c1;
    @Mock private Group p1ʹc1ʹ;
    @Mock private Group p1ʹc1c2;
    @Mock private Design design_p1c1;
    @Mock private Design design_c1;
    @Mock private Design design_p1ʹc1ʹ;
    @Mock private Design design_p1ʹc1c2;
    private PStack<Component> path_p1c1;
    private PStack<Component> path_p1ʹc1ʹ;
    private PStack<Component> path_p1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(p1c1.swap(c1, c1ʹ)).toReturn(p1ʹc1ʹ);
        stub(p1c1.add(c2)).toReturn(p1ʹc1c2);
        stub(design_p1c1.swap(rootp1c1, rootʹc1)).toReturn(design_c1);
        stub(design_p1c1.swap(rootp1c1, rootʹp1ʹc1ʹ)).toReturn(design_p1ʹc1ʹ);
        stub(design_p1c1.swap(rootp1c1, rootʹp1ʹc1c2)).toReturn(design_p1ʹc1c2);
        stub(rootp1c1.swap(p1c1, c1)).toReturn(rootʹc1);
        stub(rootp1c1.swap(p1c1, p1ʹc1ʹ)).toReturn(rootʹp1ʹc1ʹ);
        stub(rootp1c1.swap(p1c1, p1ʹc1c2)).toReturn(rootʹp1ʹc1c2);
        path_p1 = ConsPStack.<Component>singleton(rootp1c1).plus(p1c1);
        path_p1c1 = ConsPStack.<Component>singleton(rootp1c1).plus(p1c1).plus(c1);
        path_p1ʹc1ʹ = ConsPStack.<Component>singleton(rootʹp1ʹc1ʹ).plus(p1ʹc1ʹ).plus(c1ʹ);
        subject = new MutableDataModel(design_p1c1);
    }

    @Test
    public void shouldHaveInitialDesignWithMainComponent() throws Exception {
        subject = new MutableDataModel();
        assertThat(subject.design()).isNotNull();
        assertThat(subject.design().mainComponent().children()).isNotEmpty();
    }

    @Test
    public void replace_shouldUpdateDesign() throws Exception {
        subject.replace(path_p1, c1);
        assertThat(subject.design()).isSameAs(design_c1);
    }

    @Test
    public void replace_inChild_shouldUpdateDesign() throws Exception {
        subject.replace(path_p1c1, c1ʹ);
        assertThat(subject.design()).isSameAs(design_p1ʹc1ʹ);
    }

    @Test
    public void replace_shouldNotifyObservers() throws Exception {
        subject.watch(observer);
        reset(observer);
        subject.replace(path_p1c1, c1ʹ);
        verify(observer).update();
    }

    @Test
    public void replace_shouldReturnMutatedPath() throws Exception {
        final PStack<Component> newPath = subject.replace(path_p1c1, c1ʹ);
        assertThat(newPath).isEqualTo(path_p1ʹc1ʹ);
    }

    @Test
    public void add_shouldUpdateDesign() throws Exception {
        subject.add(path_p1c1, c2);
        assertThat(subject.design()).isSameAs(design_p1ʹc1c2);
    }
}
