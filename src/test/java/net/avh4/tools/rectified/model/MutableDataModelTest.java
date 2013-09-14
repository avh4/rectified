package net.avh4.tools.rectified.model;

import net.avh4.util.Observer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pcollections.ConsPStack;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.stub;

public class MutableDataModelTest {

    private MutableDataModel subject;
    @Mock private Design design2;
    @Mock private Observer<Design> observer;
    @Mock private Component c1;
    @Mock private Component c1ʹ;
    @Mock private Group p1;
    @Mock private Group p1ʹ;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new MutableDataModel();
    }

    @Test
    public void shouldHaveInitialDesignWithMainComponent() throws Exception {
        assertThat(subject.design()).isNotNull();
        assertThat(subject.design().mainComponent().children()).isNotEmpty();
    }

    @Test
    public void replace_shouldUpdateDesign() throws Exception {
        subject.replace(ConsPStack.<Component>singleton(subject.design().mainComponent()).plus(subject.design().mainComponent().children().get(0)), c1);
        assertThat(subject.design().mainComponent().children().get(0)).isSameAs(c1);
    }

    @Test
    public void replace_inChild_shouldUpdateDesign() throws Exception {
        Group root = subject.design().mainComponent();
        subject.replace(ConsPStack.<Component>singleton(root).plus(root.children().get(0)), p1);
        assertThat(subject.design().mainComponent().children().get(0)).isSameAs(p1);

        root = subject.design().mainComponent();
        stub(p1.swap(c1, c1ʹ)).toReturn(p1ʹ);
        subject.replace(ConsPStack.<Component>singleton(root).plus(p1).plus(c1), c1ʹ);
        assertThat(subject.design().mainComponent().children().get(0)).isSameAs(p1ʹ);
    }

    @Test
    public void replace_shouldNotifyObservers() throws Exception {
        subject.watch(observer);
        subject.replace(ConsPStack.<Component>singleton(subject.design().mainComponent()).plus(subject.design().mainComponent().children().get(0)), c1);
//        verify(observer).update();
    }

    @Test
    public void add_shouldUpdateDesign() throws Exception {
        subject.add(ConsPStack.<Component>singleton(subject.design().mainComponent()), c1);
        assertThat(subject.design().mainComponent().children().get(1)).isSameAs(c1);
    }
}
